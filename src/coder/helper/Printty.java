package coder.helper;

import coder.models.DishPrint;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import java.awt.*;
import java.awt.print.*;
import java.util.List;

public class Printty {
    static double x, y, w, h;

    public static void print(List<DishPrint> dishes) throws PrinterException {
        String printerName = "EPSON TM-T82 ReceiptTC4";
        PrinterJob pj = PrinterJob.getPrinterJob();

        System.out.println("We are here!");

        PrintService[] ps = PrintServiceLookup.lookupPrintServices(null, null);

        for (PrintService pp : ps) {
            if (pp.getName().contentEquals(printerName)) {
                System.out.println("We found our printer");
                PageFormat pf = pj.defaultPage();
                Paper paper = new Paper();
                double margin = 11;
                paper.setImageableArea(margin, margin, paper.getWidth() - (margin * 2), ((paper.getHeight() - margin * 2)) / 2);
                pj.setPrintService(pp);
                pf.setPaper(paper);
                pj.defaultPage(pf);
                pj.setPrintable(new Printable() {
                    @Override
                    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                        x = pf.getImageableX();
                        y = pf.getImageableY();
                        w = pf.getImageableWidth() - (x * 2);
                        h = pf.getImageableHeight() - (y * 3);

                        if (pageIndex < 0 || pageIndex >= 1) {
                            return Printable.NO_SUCH_PAGE;
                        }

                        Graphics2D g2d = (Graphics2D) graphics;

                        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

                        drawTitle(g2d);
                        drawHeader(g2d);
                        drawHoz(g2d);
                        setValue(g2d, dishes);
                        drawTotal(g2d, dishes);
                        drawTax(g2d, dishes);
                        drawGrandTotal(g2d, dishes);
                        tankline(g2d, dishes);


                        return Printable.PAGE_EXISTS;
                    }
                }, pf);
                pj.print();
            }
        }

    }

    private static void drawTitle(Graphics2D g) {
        Font f = new Font("Cambria", Font.BOLD, 12);
        g.setFont(f);
        g.drawString("ေငြတာရီ", X(12), Y(0));
    }

    private static void drawHeader(Graphics2D g) {
        Font f = new Font("Zawgyi-One", Font.PLAIN, 8);
        g.setFont(f);
        int y = 5;
        g.drawString("စဥ္", X(1), Y(y));
        g.drawString("Name", X(7), Y(y));
        g.drawString("Price", X(18), Y(y));
        g.drawString("Qty", X(24), Y(y));
        g.drawString("Total", X(30), Y(y));
    }

    private static void drawHoz(Graphics2D g) {
        g.drawLine(X(0), Y(6), X(57), Y(6));
    }

    private static void setValue(Graphics2D g, List<DishPrint> dishes) {
        Font f = new Font("Zawgyi-One", Font.PLAIN, 8);
        g.setFont(f);
        for (int i = 0; i < dishes.size(); i++) {
            int t = i + 1;
            int y = 4 * i;

            g.drawString(String.valueOf(t), X(1), Y(10 + y));
            g.drawString(dishes.get(i).getName(), X(7), Y(10 + y));
            g.drawString(String.valueOf(dishes.get(i).getPrice()), X(18), Y(10 + y));
            g.drawString(String.valueOf(dishes.get(i).getQty()), X(24), Y(10 + y));
            g.drawString(String.valueOf(dishes.get(i).getTotal()), X(30), Y(10 + y));
        }
    }

    private static void drawTotal(Graphics2D g, List<DishPrint> dishes) {
        Font f = new Font("Cambria", Font.PLAIN, 8);
        g.setFont(f);
        g.drawString("Total", X(25), Y(14 + (dishes.size() * 4)));

        int total = 0;
        for (DishPrint dish : dishes) {
            total += dish.getTotal();
        }
        g.drawString(String.valueOf(total), X(30), Y(14 + (dishes.size() * 4)));
    }

    private static void drawTax(Graphics2D g, List<DishPrint> dishes) {
        Font f = new Font("Cambria", Font.PLAIN, 8);
        g.setFont(f);
        g.drawString("Tax", X(25), Y(18 + (dishes.size() * 4)));
        int total = 0;
        for (DishPrint dish : dishes) {
            total += dish.getTotal();
        }
        g.drawString(String.valueOf(total*0.05), X(30), Y(18 + (dishes.size() * 4)));
    }

    private static void drawGrandTotal(Graphics2D g, List<DishPrint> dishes) {
        Font f = new Font("Cambria", Font.PLAIN, 8);
        g.setFont(f);
        g.drawString("Grand Total", X(22), Y(22 + (dishes.size() * 4)));
        int total = 0;
        for (DishPrint dish : dishes) {
            total += dish.getTotal();
        }
        g.drawString(String.valueOf(total+(total*0.05)), X(30), Y(22 + (dishes.size() * 4)));
    }

    private static void tankline(Graphics2D g, List<DishPrint> dishes) {
        Font f = new Font("Zawgyi-One", Font.PLAIN, 8);
        g.setFont(f);
        g.drawString("ေက်းဇူး အထူး တင္ရွိပါသည္", X(7), Y(26 + (dishes.size() * 4)));
    }


    private static int X(double num) {
        return ((int) (x + ((w / 100) * num)));
    }

    private static int Y(double num) {
        return ((int) (y + ((h / 100) * num)));
    }
}

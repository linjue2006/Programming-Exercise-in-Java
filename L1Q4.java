package MyProfile.L1Q4;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * L1Q4 - Total Sales of Product A (Bar Chart)
 * Months: Jan–Jun 2016 with amounts from the assignment table.
 */
public class L1Q4 extends JFrame {
    public L1Q4() {
        super("L1Q4 - Total Sales of Product A");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Assignment data (Month -> Amount)
        Map<String, Double> data = new LinkedHashMap<>();
        data.put("January 2016", 2500.0);
        data.put("February 2016", 1600.0);
        data.put("March 2016", 2000.0);
        data.put("April 2016", 2700.0);
        data.put("May 2016", 3200.0);
        data.put("June 2016", 800.0);

        setContentPane(new BarChartPanel(data, "Month", "Amount"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception ignored) {}
            new L1Q4().setVisible(true);
        });
    }
}

class BarChartPanel extends JPanel {
    private final Map<String, Double> data;
    private final String xLabel;
    private final String yLabel;

    private final int leftPad = 80;
    private final int rightPad = 40;
    private final int topPad = 40;
    private final int bottomPad = 80;

    public BarChartPanel(Map<String, Double> data, String xLabel, String yLabel) {
        this.data = data;
        this.xLabel = xLabel;
        this.yLabel = yLabel;
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        int plotX = leftPad;
        int plotY = topPad;
        int plotW = width - leftPad - rightPad;
        int plotH = height - topPad - bottomPad;

        // Plot background
        g2.setColor(new Color(245, 245, 245));
        g2.fillRect(plotX, plotY, plotW, plotH);
        g2.setColor(new Color(200, 200, 200));
        g2.drawRect(plotX, plotY, plotW, plotH);

        int n = data.size();
        double maxVal = data.values().stream().mapToDouble(v -> v).max().orElse(1.0);
        if (maxVal == 0) maxVal = 1.0;

        drawAxesAndTicks(g2, plotX, plotY, plotW, plotH, maxVal);

        // Bars
        double barSlotW = (double) plotW / n;      // slot width per category
        double barW = barSlotW * 0.6;              // actual bar width
        double startX = plotX + barSlotW * 0.2;    // center bars in slot

        int i = 0;
        for (Map.Entry<String, Double> e : data.entrySet()) {
            String category = e.getKey();
            double value = e.getValue();
            double ratio = value / maxVal;
            int barH = (int) Math.round(ratio * plotH);
            int x = (int) Math.round(startX + i * barSlotW);
            int y = plotY + plotH - barH;

            // Bar color gradient
            Color base = new Color(92, 146, 250);
            Color darker = base.darker();
            g2.setPaint(new GradientPaint(x, y, base, x, y + barH, darker));
            g2.fill(new Rectangle2D.Double(x, y, barW, barH));

            // Value label
            g2.setColor(new Color(40, 40, 40));
            String valText = String.format("%.0f", value);
            int valW = g2.getFontMetrics().stringWidth(valText);
            g2.drawString(valText, (int) (x + barW / 2 - valW / 2), y - 6);

            // Category label (X axis)
            g2.setColor(new Color(30, 30, 30));
            String catText = category;
            int catW = g2.getFontMetrics().stringWidth(catText);
            int catY = plotY + plotH + 24;
            g2.drawString(catText, (int) (x + barW / 2 - catW / 2), catY);

            i++;
        }

        // Axis labels
        g2.setColor(new Color(50, 50, 50));
        String xLab = xLabel;
        int xLabW = g2.getFontMetrics().stringWidth(xLab);
        g2.drawString(xLab, plotX + plotW / 2 - xLabW / 2, plotY + plotH + 50);
        drawRotatedLeft(g2, yLabel, plotX - 50, plotY + plotH / 2);

        g2.dispose();
    }

    private void drawAxesAndTicks(Graphics2D g2, int x, int y, int w, int h, double maxVal) {
        g2.setColor(new Color(120, 120, 120));
        // Axes
        g2.drawLine(x, y + h, x + w, y + h); // X axis
        g2.drawLine(x, y, x, y + h);         // Y axis

        // Y ticks: 5
        int ticks = 5;
        for (int i = 0; i <= ticks; i++) {
            double ratio = i / (double) ticks;
            int ty = y + h - (int) Math.round(ratio * h);
            g2.setColor(new Color(210, 210, 210));
            g2.drawLine(x, ty, x + w, ty); // grid line
            g2.setColor(new Color(80, 80, 80));
            double tickVal = ratio * maxVal;
            String t = formatTick(tickVal);
            int tw = g2.getFontMetrics().stringWidth(t);
            g2.drawString(t, x - tw - 10, ty + 5);
        }
    }

    private String formatTick(double v) {
        if (v >= 1000) {
            return String.format("%.0f", v);
        } else {
            return String.format("%.0f", v);
        }
    }

    private void drawRotatedLeft(Graphics2D g2, String text, int cx, int cy) {
        FontMetrics fm = g2.getFontMetrics();
        int tw = fm.stringWidth(text);
        int th = fm.getAscent();
        g2.translate(cx, cy);
        g2.rotate(-Math.PI / 2);
        g2.drawString(text, -tw / 2, th / 2);
        g2.rotate(Math.PI / 2);
        g2.translate(-cx, -cy);
    }
}
package com.kodilla.simplejavagame;

import javafx.scene.input.MouseEvent;

public class exePrint {


static int gridCol = 1;
static int gridRow = 1;

    public exePrint(int gridCol, int gridRow) {
        this.gridCol = gridCol;
        this.gridRow = gridRow;
    }

    public int getGridCol() {
        return gridCol;
    }

    public void setGridCol(int gridCol) {
        this.gridCol = gridCol;
    }

    public int getGridRow() {
        return gridRow;
    }

    public void setGridRow(int gridRow) {
        this.gridRow = gridRow;
    }

    static exePrint zbieraczGrida = new exePrint(gridRow, gridCol);
    public int bioreGridCol() {
    return zbieraczGrida.getGridCol();
}
    public int bioreGridRow() {
        return zbieraczGrida.getGridRow();
    }


    public static void print2(MouseEvent e) {

        String typ = e.getEventType().getName();
        String source = e.getSource().getClass().getSimpleName();
        String target = e.getTarget().getClass().getSimpleName();
        double sx = e.getSceneX();
        double sy = e.getSceneY();
        double ex = e.getScreenX();
        double ey = e.getScreenY();
        if (sx >= 92 && sx <= 177 && sy >= 188 && sy <= 282) {
            System.out.println("Jesteś w gridzie r0 c2");
            int row = 0;
            int col = 1;
            zbieraczGrida.setGridRow(row);
            System.out.println("rzad zbieracza grida " + zbieraczGrida.getGridRow());
            zbieraczGrida.setGridCol(col);
            System.out.println("kolumna zbieracza grida " + zbieraczGrida.getGridCol());
        } else if (sx >= 208 && sx <= 305 && sy >= 177 && sy <= 274){
                System.out.println("Jesteś w gridzie r0 c3");
                int row = 0;
                int col = 2;
            zbieraczGrida.setGridRow(row);
            System.out.println("rzad zbieracza grida " + zbieraczGrida.getGridRow());
            zbieraczGrida.setGridCol(col);
            System.out.println("kolumna zbieracza grida " + zbieraczGrida.getGridCol());
            }

        System.out.println("Typ zdarzenia: " + typ + ", Żródło zdarzenia: " + source + ", Cel zdarzenia: " + target);
        System.out.println("Koordynaty zdarzenia na scenie: [" + sx + ", " + sy + "]");
        System.out.println("Koordynaty zdarzenia na wyświetlaczu: [" + ex + ", " + ey + "]");


    }
}





package Lecture_16.HomeWork;

public class Ship implements Runnable {
    private int shipNumber;
    private int boxesCount;
    private Port port;

    public Ship(int shipNumber, int boxesCount, Port port) {
        this.shipNumber = shipNumber;
        this.boxesCount = boxesCount;
        this.port = port;
    }

    public int getBoxesCount() {
        return boxesCount;
    }

    public void setBoxesCount(int boxesCount) {
        this.boxesCount = boxesCount;
    }

    public int getShipNumber() {
        return shipNumber;
    }

    @Override
    public void run() {
        try {
            port.getDock().unloadShip(this);
            /*if (port.getDock() != null) {
                port.getDock().unloadShip(this);
            }*/
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

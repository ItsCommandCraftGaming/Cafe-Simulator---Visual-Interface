package proiect;


public class ThreadViata extends Thread {
    private int viata, scor;
    private boolean ruleaza = true;

    public ThreadViata(int viata){  //Viata efectiva
        this.viata=viata;
        this.scor=0;
    }

    public int getScor(){
        return this.scor;
    }

    public double getStatusBar(){
        return this.viata;
    }

    public void oprire(){          //Oprire thread
        ruleaza = false;
    }

    public synchronized void addViata(int addViata){    //Adaugare viata
        this.viata+=addViata;
    }

    @Override
    public void run() {   //Proces de rulare
        while (ruleaza && viata > 0) {
            viata -= 1;
            scor +=5;
        
            try {
                Thread.sleep(1000);   //Viata scade cu 1s tot mereu
            } 
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
    }
    
}

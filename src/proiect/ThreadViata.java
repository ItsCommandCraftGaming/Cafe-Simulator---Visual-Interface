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
        System.out.println("+"+"\u001B[42m\u001B[1m"+addViata+"\u001B[0m");
    }

    @Override
    public void run() {   //Proces de rulare
        while (ruleaza && viata > 0) {
            synchronized (this) {
                viata -= 1;
                scor +=5;
            }
            try {
                Thread.sleep(1000);   //Viata scade cu 1s tot mereu
            } 
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (viata==0){                   //Daca viata ajunge la 0, se opreste programul
                System.out.println("\nGame over!!\nScor: "+getScor());
            }
            
        }
    }

    
}

package git;

import java.util.concurrent.Semaphore;

public class Git extends Thread{

        private int idThread;
        private Semaphore semaforo;
        
        public Git(int id, Semaphore semaphore){
            this.idThread = id;
            this.semaforo = semaphore;
        }
        
        private void processar(){
            try{
                System.out.println("Thread #"+idThread+"processando");
                Thread.sleep((long)(Math.random()*10000));
            }catch(Exception e ){
                e.printStackTrace();
            }
        }
        
        private void entrarRegiaoCritica(){
            System.out.println("thread #"+idThread
                                +"entrando em regi�o cr�tica");
            processar();
            System.out.println("Thread #"+idThread + "saindo da regi�o cr�tica");
        }
        
        public void run(){
            entrarRegiaoCritica();
            try {
                semaforo.acquire();
                entrarRegiaoCritica();
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                semaforo.release();
            }
        }
}



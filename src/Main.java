import java.util.Random;

public class Main {
    public static int bossHealth=3000;
    public static int bossDamage=50;
    public static String bossDefenceType="";
    public static Random random = new Random();


    public static int[] heroesHealth={270,280,260,270,200,260,750,101};
    public static int[] heroesDamage={20,15,25,25,15,20,5,0};
    public static String[] heroesAttackType={"Physical","Kinetic","Magical","Thor",
            "Lucky","Berserk","Golem","Doctor"};
    public static int toTreat=30;
    public static int q=0;
    public static int roundNum=0;

    public static void main(String[] args) {
        printstatics();
        while(!isGameFinished()){
            roundNum++;
            System.out.println("******* Round "+roundNum+" *******");
            round ();
        }


    }
    public static void printstatics(){

        System.out.println("BossHealth: "+bossHealth+"["+bossDamage+"]");
        for(int i=0;i<heroesHealth.length;++i){
            System.out.println(heroesAttackType[i]+" health; "+heroesHealth[i]+"["+heroesDamage[i]+"]");
        }
    }
    public static void bossHits(){
        Random r=new Random();
        q=r.nextInt(10);
        if(roundNum>1 && q==9) {
            bossDamage+=0;
            System.out.println("Boss was stunned!");

        }else {
            for (int i = 0; i < heroesHealth.length; ++i) {

                if (heroesHealth[i] < bossDamage) {
                    heroesHealth[i] = 50;
                }
                heroesHealth[i] -= bossDamage;


            }
        }

    }
    public static void heroesHits(){
        for(int i=0;i<heroesHealth.length-1;++i){

            if(heroesHealth[i]>0 && bossHealth>0 ){
                bossHealth-=heroesDamage[i];
                if(bossDefenceType==heroesAttackType[i]){
                    Random r=new Random();
                    int coef=r.nextInt(5)+2;
                    bossHealth-=heroesDamage[i]*coef;
                    if(bossHealth<heroesDamage[i]*coef) {
                        bossHealth=0;

                    }else {
                        bossHealth-=heroesDamage[i]*coef;
                    }

                    System.out.println("Critical Damage -"+heroesDamage[i]*coef);
                }
                else{
                    if(bossHealth<heroesDamage[i]){
                        bossHealth=0;
                    }else {
                        bossHealth -= heroesDamage[i];
                    }
                }
            }

        }

    }
    public static void whoWastreated(){
        int f=random.nextInt(7);
        for(int i=0;i<7;i++){
            if(i==f && heroesHealth[7]>0){
                heroesHealth[i]+=toTreat;
                System.out.println(heroesAttackType[i]+" was treated!");
            }else{
                heroesHealth[i]+=0;
            }
        }
    }
    public static void missing(){
        int y=random.nextInt(4);
        if(y==3){
            heroesHealth[4]+=50;
            System.out.println(heroesAttackType[4]+" was missed!");
        }
    }
    public static void golemsAbility(){
        int p=random.nextInt(5);
        int l=0;
        if(p==0){
            for(int i=0;i<6;i++){
                if(heroesHealth[i]>0) {
                    heroesHealth[i] += 10;
                    l+=10;
                }else {
                    heroesHealth[i]+=0;
                }
            }
            if(heroesHealth[7]>0) {
                heroesHealth[7] += 10;
                l+=10;
            }else {
                heroesHealth[6] -= l;
            }
            System.out.println("Golem protected!");

        }else{
            heroesHealth[6]+=0;
        }

    }
    public static void berserkAbiility(){
        int p=random.nextInt(4)+1;
        heroesHealth[5]+=p*10;
        bossHealth-=p*10;
        System.out.println("Berserks ability damage:"+(p*10));

    }


    public static void round(){
        bossHits();
        heroesHits();
        missing();
        golemsAbility();
        berserkAbiility();
        printstatics();
        whoWastreated();
        if(bossHealth<0) {
            bossHealth=0;
        }
        chooseBossDefenceType();
    }
    public static Boolean isGameFinished(){
        if(bossHealth<=0){
            bossHealth=0;
            System.out.println("Heroes won!");
            return true;
        }
        if(heroesHealth[0]<=0 && heroesHealth[1]<=0 && heroesHealth[2]<=0 && heroesHealth[3]<=0
                && heroesHealth[4]<=0 && heroesHealth[5]<=0 && heroesHealth[6]<=0 && heroesHealth[7]<=0    ){
            System.out.println("Defeat");
            return true;
        }
        return false;
    }
    public static void chooseBossDefenceType(){
        int randomIndex=random.nextInt(3);
        bossDefenceType=heroesAttackType[randomIndex];
        System.out.println("Boss chose: "+bossDefenceType);
    }
}
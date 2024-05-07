public abstract class WordStep {
     String word;
     int cost;

    public WordStep() {
        this.word = "";
        this.cost = 0;
    }
    public WordStep(String word, int cost) {
        this.word = word;
        this.cost = cost;
    }

}


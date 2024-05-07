public class WordStepAstar extends WordStep {
    private String word;
    private int cost;  // g(x): Cost from start
    private int totalCost;  // f(x): g(x) + h(x)
    WordStepAstar parent;

    public WordStepAstar(String word, int cost, int totalCost, WordStepAstar parent) {
        this.word = word;
        this.cost = cost;
        this.totalCost = totalCost;
        this.parent = parent;
    }

    public WordStepAstar getPred() {
        return this.parent;
    }

    public String getWord() {
        return this.word;
    }

    public int getTotalCost() {
        return this.totalCost;
    }

    public int getCost() {
        return this.cost;
    }

}

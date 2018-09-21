package datamodel;

public class BattleResult {

    public static BattleResult battleWon = new BattleResult(0, "WON");
    public static BattleResult battleLost = new BattleResult(1, "LOST");
    public static BattleResult battleTie = new BattleResult(2, "TIE");
    int battleResultID;
    String literal;

    public BattleResult(int battleResultID, String literal) {
        this.battleResultID = battleResultID;
        this.literal = literal;
    }

    public int intValue(){
        return  battleResultID;
    }

    public static BattleResult won() {
        return battleWon;
    }

    public static BattleResult lost() {
        return battleLost;
    }

    public static BattleResult tie() {
        return battleTie;
    }

    public static BattleResult get(int ID) {
        switch (ID) {
            case 0: {
                return battleWon;
            }

            case 1: {
                return battleLost;
            }

            case 2: {
                return battleTie;
            }

        }
        return null;
    }

    @Override
    public String toString() {
        return literal;
    }
}

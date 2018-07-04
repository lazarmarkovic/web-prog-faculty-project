package beans.enums;

public enum NivoHitnosti {
	CRVENO(1), NARANDZASTO(2), PLAVO(3);
	
	private final int value;
    private NivoHitnosti(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

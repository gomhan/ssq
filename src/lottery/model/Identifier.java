package lottery.model;

/**
 * A integer numeric identifier.
 */
public class Identifier implements Comparable<Identifier> {

	private final Integer key;

	public static final Identifier createIdentifier(int key) {
		Identifier identifier = new Identifier(key);
		return identifier;
	}

	private Identifier(int key) {
		// TODO Auto-generated constructor stub
		this.key = new Integer(key);
	}

	public int getValue() {
		return key.intValue();
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		if (key == null) {
			return 0;
		}
		return key.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj == null || !(obj instanceof Integer)) {
			return false;
		}
		Integer dest = (Integer) obj;
		return key.equals(dest);
	}

	@Override
	public int compareTo(Identifier another) {
		// TODO Auto-generated method stub
		int v2 = 0;
		if (another != null) {
			v2 = another.getValue();
		}
		int v1 = getValue();
		return v1 - v2;
	}
}

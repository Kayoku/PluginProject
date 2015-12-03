package plugins;

public class PremierPlugin implements Plugin {

	public String transform(String s) {
		return "i love pinkie because she is sooo random";
	}

	public String getLabel() {		
		return "PremierPlugin";
	}

}

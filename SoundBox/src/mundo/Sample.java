package mundo;



/**
 * @author Sebastian
 * @version 1.0
 * @created 04-Feb-2014 11:09:35 PM
 */
public class Sample implements ISonido, Comparable<Sample> {

	private String src;

	public Sample(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	/**
	 * 
	 * @param nSrc
	 */
	public void Sample(String nSrc){

	}

	public String darSrc(){
		return "";
	}

	@Override
	public int compareTo(Sample arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
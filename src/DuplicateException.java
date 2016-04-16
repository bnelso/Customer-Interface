
public class DuplicateException extends RuntimeException{
	private static final long serialVersionUID = 1L;

    public DuplicateException(){
        super("Duplicate keys not allowed.");
    }
}

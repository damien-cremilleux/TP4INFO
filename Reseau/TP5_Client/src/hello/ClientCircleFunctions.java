package hello;

public class ClientCircleFunctions {
	public static void main(String[] args) {
		System.out.println("a");
		try { // Call Web Service Operation
           hello.CircleFunctionsService service =
                new hello.CircleFunctionsService();
            hello.CircleFunctions port = service.getCircleFunctionsPort();
            // TODO initialize WS operation arguments here
            double arg0 = 3.0;
            // TODO process result here
            double result = port.getArea(arg0);
            System.out.println("Result = "+result);
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        // TODO code application logic here
    }

}

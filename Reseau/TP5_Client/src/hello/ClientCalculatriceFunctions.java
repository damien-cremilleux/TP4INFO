package hello;

public class ClientCalculatriceFunctions {
	public static void main(String[] args) {
		System.out.println("a");
		try { // Call Web Service Operation
           hello.CalculatriceFunctionsService service =
                new hello.CalculatriceFunctionsService();
            hello.CalculatriceFunctions port = service.getCalculatriceFunctionsPort();
            // TODO initialize WS operation arguments here
            double arg0 = 3.0;
            double arg1 = 2.0;
            // TODO process result here
            double result = port.moins(arg0, arg1);
            System.out.println("Result = "+result);
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        // TODO code application logic here
    }

}

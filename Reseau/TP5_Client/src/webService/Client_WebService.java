package webService;

import net.webservicex.BibleWebservice;
import net.webservicex.BibleWebserviceSoap;
import net.webservicex.Braille;
import net.webservicex.TranslateServiceSoap;

public class Client_WebService {

	public static void main(String[] args) {
		System.out.println("a");
		try { // Call Web Service Operation
			net.webservicex.BibleWebservice service =
                new net.webservicex.BibleWebservice();
			net.webservicex.Braille service2 = new Braille();
			
            BibleWebserviceSoap port = service.getBibleWebserviceSoap();
            // TODO initialize WS operation arguments here
            //double arg0 = 3.0;
            //double arg1 = 2.0;
            // TODO process result here
            String result = port.getBibleWordsbyKeyWord("jeslus");
            System.out.println("Result = "+result);
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        	System.out.println(ex.getMessage());
        }
        // TODO code application logic here
    }

}

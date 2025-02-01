package ma.ensaj.soap_front.soap;


import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import ma.ensaj.soap_front.models.Compte;

public class SoapClient {
    private static final String NAMESPACE = "http://ws.gestion_compte.gestion_compte/";
    private static final String METHOD_NAME = "getAllComptes";
    private static final String URL = "http://10.0.2.2:8082/services/ws?wsdl";

    public static Object callWebService(String methodName, SoapObject request) throws Exception {

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);

        HttpTransportSE transport = new HttpTransportSE(URL);
        String soapAction = NAMESPACE + methodName;

        transport.call("", envelope);

        return envelope.getResponse();
    }

    public List<Compte> getAllComptes() {
        List<Compte> comptes = new ArrayList<>();
        try {
            SoapObject request = new SoapObject(NAMESPACE, "getComptes");
            Object response = SoapClient.callWebService("getComptes", request);

            if (response instanceof Vector) {

                Vector<?> responseVector = (Vector<?>) response;
                for (Object item : responseVector) {
                    if (item instanceof SoapObject) {
                        comptes.add(parseCompte((SoapObject) item));
                    }
                }
            } else if (response instanceof SoapObject) {

                comptes.add(parseCompte((SoapObject) response));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comptes;
    }
    private Compte parseCompte(SoapObject soapObject) {
        return new Compte(
                Long.parseLong(soapObject.getProperty("id").toString()),
                Double.parseDouble(soapObject.getProperty("solde").toString()),
                soapObject.getProperty("dateCreation").toString(),
                soapObject.getProperty("type").toString()
        );
    }

    public boolean updateCompte(Compte compte) {
        try {

            SoapObject request = new SoapObject(NAMESPACE, "updateCompte");

            String formattedSolde = String.format("%.2f", compte.getSolde());
            request.addProperty("id", compte.getId());
            request.addProperty("solde", formattedSolde);
            request.addProperty("dateCreation", compte.getDateCreation());
            request.addProperty("type", compte.getType());

            SoapClient.callWebService("updateCompte", request);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCompte(Long id) {
        try {
            SoapObject request = new SoapObject(NAMESPACE, "deleteCompte");
            request.addProperty("id", id);

            SoapClient.callWebService("deleteCompte", request);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}



@Service
public class JsonToXMLConverterServiceImpl implements JsonToXMLConverterService {

    @Autowired
    private ServiceRouter serviceRouter;
    
    @Value("${ddswipr.input.endpoint}")
    private String inputEndpoint;
    
    private static final Logger logger = LoggerFactory.getLogger(JsonToXMLConverterServiceImpl.class);

    @Override
    public String convertDto(BatchSummaryRequest batchSummaryRequest) {
        try {
            StringWriter stringWriter = new StringWriter();
            JAXBContext jaxbContext = JAXBContext.newInstance(BatchSummaryRequest.class);
            Marshaller m = jaxbContext.createMarshaller();
            m.marshal(batchSummaryRequest, stringWriter);
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            String xml = stringWriter.toString();
            sendToInputAPI(xml);
            return xml;
        } catch (Exception e) {
            logger.error("Exception occurred while converting to XML ", e);
            throw new RuntimeException(e);
        }
    }

    private void sendToInputAPI(String xml) {
      
      logger.error("Output XML is {} ", xml);
      try {
          serviceRouter.put(inputEndpoint, xml, null, "8748",String.class, new ServiceRouterErrorHandler());
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

}


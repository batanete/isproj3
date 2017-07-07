
package clientws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the clientws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ListSubsResponse_QNAME = new QName("http://ws/", "listSubsResponse");
    private final static QName _DelSub_QNAME = new QName("http://ws/", "delSub");
    private final static QName _ListSubs_QNAME = new QName("http://ws/", "listSubs");
    private final static QName _DelSubResponse_QNAME = new QName("http://ws/", "delSubResponse");
    private final static QName _CreateSubResponse_QNAME = new QName("http://ws/", "createSubResponse");
    private final static QName _CreateSub_QNAME = new QName("http://ws/", "createSub");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: clientws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ListSubsResponse }
     * 
     */
    public ListSubsResponse createListSubsResponse() {
        return new ListSubsResponse();
    }

    /**
     * Create an instance of {@link DelSub }
     * 
     */
    public DelSub createDelSub() {
        return new DelSub();
    }

    /**
     * Create an instance of {@link ListSubs }
     * 
     */
    public ListSubs createListSubs() {
        return new ListSubs();
    }

    /**
     * Create an instance of {@link DelSubResponse }
     * 
     */
    public DelSubResponse createDelSubResponse() {
        return new DelSubResponse();
    }

    /**
     * Create an instance of {@link CreateSubResponse }
     * 
     */
    public CreateSubResponse createCreateSubResponse() {
        return new CreateSubResponse();
    }

    /**
     * Create an instance of {@link CreateSub }
     * 
     */
    public CreateSub createCreateSub() {
        return new CreateSub();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListSubsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws/", name = "listSubsResponse")
    public JAXBElement<ListSubsResponse> createListSubsResponse(ListSubsResponse value) {
        return new JAXBElement<ListSubsResponse>(_ListSubsResponse_QNAME, ListSubsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DelSub }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws/", name = "delSub")
    public JAXBElement<DelSub> createDelSub(DelSub value) {
        return new JAXBElement<DelSub>(_DelSub_QNAME, DelSub.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListSubs }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws/", name = "listSubs")
    public JAXBElement<ListSubs> createListSubs(ListSubs value) {
        return new JAXBElement<ListSubs>(_ListSubs_QNAME, ListSubs.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DelSubResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws/", name = "delSubResponse")
    public JAXBElement<DelSubResponse> createDelSubResponse(DelSubResponse value) {
        return new JAXBElement<DelSubResponse>(_DelSubResponse_QNAME, DelSubResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateSubResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws/", name = "createSubResponse")
    public JAXBElement<CreateSubResponse> createCreateSubResponse(CreateSubResponse value) {
        return new JAXBElement<CreateSubResponse>(_CreateSubResponse_QNAME, CreateSubResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateSub }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws/", name = "createSub")
    public JAXBElement<CreateSub> createCreateSub(CreateSub value) {
        return new JAXBElement<CreateSub>(_CreateSub_QNAME, CreateSub.class, null, value);
    }

}

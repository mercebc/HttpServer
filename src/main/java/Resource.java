import java.util.Objects;

public class Resource {
    private String method;
    private String URI;

    public Resource(String method, String URI) {
        this.method = method;
        this.URI = URI;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getURI() {
        return URI;
    }

    public void setURI(String URI) {
        this.URI = URI;
    }

    @Override
    public boolean equals(Object r) {

        if (r == this) return true;
        if (!(r instanceof Resource)) {
            return false;
        }
        Resource resource = (Resource) r;
        return Objects.equals(URI, resource.URI) &&
                Objects.equals(method, resource.method);
    }

    @Override
    public int hashCode() {
        return Objects.hash(URI, method);
    }

}

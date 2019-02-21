import java.util.Objects;

public class Route {
    private String method;
    private String URI;

    public Route(String method, String URI) {
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
        if (!(r instanceof Route)) {
            return false;
        }
        Route route = (Route) r;
        return Objects.equals(URI, route.URI) &&
                Objects.equals(method, route.method);
    }

    @Override
    public int hashCode() {
        return Objects.hash(URI, method);
    }

}

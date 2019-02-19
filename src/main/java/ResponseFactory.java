import Responses.*;
import Responses.OKOldResponse;
import Responses.OldResponse;

public class ResponseFactory {

    public OldResponse getResponse(String shapeType) {
        if (shapeType == null) {
            return null;
        }
        if (shapeType.equalsIgnoreCase("CIRCLE")) {
            return new OKOldResponse();

        } else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
            return new NotFoundOldResponse();

        } else if (shapeType.equalsIgnoreCase("SQUARE")) {
            return new NotAllowedOldResponse();
        }

        return null;

    }
}

package eu.europa.esig.dss.pdf.pdfbox.visible;

import org.apache.pdfbox.pdmodel.PDPage;

import eu.europa.esig.dss.pades.SignatureImageParameters;
import eu.europa.esig.dss.pades.SignatureImageParameters.VisualSignatureRotation;

public class ImageRotationUtils {
	
	protected static final String SUPPORTED_ANGLES_ERROR_MESSAGE = "rotation angle must be 90, 180, 270 or 360 (0)";

	protected static final int ANGLE_360 = 360;
	protected static final int ANGLE_90 = 90;
	protected static final int ANGLE_180 = 180;
	protected static final int ANGLE_270 = 270;
	
    private static boolean needRotation(SignatureImageParameters.VisualSignatureRotation visualSignatureRotation) {
        return visualSignatureRotation != null && !SignatureImageParameters.VisualSignatureRotation.NONE.equals(visualSignatureRotation);
    }
    
    /**
     * Returns rotation parameter not depending on the page rotation
     * @param visualSignatureRotation {@link VisualSignatureRotation}
     * @return int rotation angle
     */
    public static int getRotation(VisualSignatureRotation visualSignatureRotation) {
    	return getRotation(visualSignatureRotation, 0);
    }

    /**
     * Returns rotation based on the page's default rotation parameter
     * @param visualSignatureRotation {@link VisualSignatureRotation}
     * @param pdPage {@link PDPage} to get default rotation from
     * @return int rotation angle
     */
    public static int getRotation(VisualSignatureRotation visualSignatureRotation, PDPage pdPage) {
        return getRotation(visualSignatureRotation, pdPage.getRotation());
    }
    
    private static int getRotation(VisualSignatureRotation visualSignatureRotation, int pageDefaultRotation) {
        int rotate = ANGLE_360;

        if(needRotation(visualSignatureRotation)) {
            switch (visualSignatureRotation) {
                case AUTOMATIC:
                    rotate = ANGLE_360 - pageDefaultRotation;
                    break;
                case ROTATE_90:
                    rotate = ANGLE_90;
                    break;
                case ROTATE_180:
                    rotate = ANGLE_180;
                    break;
                case ROTATE_270:
                    rotate = ANGLE_270;
                    break;
                default:
                    throw new IllegalStateException(SUPPORTED_ANGLES_ERROR_MESSAGE);
            }
        }

        return rotate;
    }
    
}

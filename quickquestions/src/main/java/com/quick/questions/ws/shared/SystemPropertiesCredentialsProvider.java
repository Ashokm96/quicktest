package com.quick.questions.ws.shared;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;

public class SystemPropertiesCredentialsProvider implements AWSCredentialsProvider {

    /** System property name for the AWS access key ID */
    private static final String ACCESS_KEY_PROPERTY = "AKIASCWPUIKGJLUD5B65";

    /** System property name for the AWS secret key */
    private static final String SECRET_KEY_PROPERTY = "kVfQogPRxl7P2DpzJOtJsNaojBz2bZ/fOT1At3FZ";

    public AWSCredentials getCredentials() {
        if (System.getProperty(ACCESS_KEY_PROPERTY) != null &&
            System.getProperty(SECRET_KEY_PROPERTY) != null) {
            return new BasicAWSCredentials(
                    System.getProperty(ACCESS_KEY_PROPERTY),
                    System.getProperty(SECRET_KEY_PROPERTY));
        }

        throw new AmazonClientException(
                "Unable to load AWS credentials from Java system properties " +
                "(" + ACCESS_KEY_PROPERTY + " and " + SECRET_KEY_PROPERTY + ")");
    }

    public void refresh() {}

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}

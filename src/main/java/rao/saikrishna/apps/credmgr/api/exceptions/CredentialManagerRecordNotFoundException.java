/* Sai Katterishetty (C) 2021 */
package rao.saikrishna.apps.credmgr.api.exceptions;

public class CredentialManagerRecordNotFoundException extends RuntimeException {

    public CredentialManagerRecordNotFoundException(String errorMessage) {
        super(errorMessage);
    }

    public CredentialManagerRecordNotFoundException(String errorMessage, Exception rootException) {
        super(errorMessage, rootException);
    }
}

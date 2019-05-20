/**
 * NOTE: This class is auto generated by the swagger code generator program (unset).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package de.adorsys.xs2a.gateway.api;

import de.adorsys.xs2a.gateway.model.ais.*;
import de.adorsys.xs2a.gateway.model.ais.error.*;
import io.swagger.annotations.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-01-11T12:48:04.675377+02:00[Europe/Kiev]")
@Api(value = "v1", description = "account API")
public interface AccountApi {

    @ApiOperation(value = "Read Account List", nickname = "getAccountList", notes = "Read the identifiers of the available payment account together with  booking balance information, depending on the consent granted.  It is assumed that a consent of the PSU to this access is already given and stored on the ASPSP system.  The addressed list of accounts depends then on the PSU ID and the stored consent addressed by consentId,  respectively the OAuth2 access token.   Returns all identifiers of the accounts, to which an account access has been granted to through  the /consents endpoint by the PSU.  In addition, relevant information about the accounts and hyperlinks to corresponding account  information resources are provided if a related consent has been already granted.  Remark: Note that the /consents endpoint optionally offers to grant an access on all available  payment accounts of a PSU.  In this case, this endpoint will deliver the information about all available payment accounts  of the PSU at this ASPSP. ", response = AccountListTO.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = AccountListTO.class),
        @ApiResponse(code = 400, message = "Bad Request", response = Error400NGAIS.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = Error401NGAIS.class),
        @ApiResponse(code = 403, message = "Forbidden", response = Error403NGAIS.class),
        @ApiResponse(code = 404, message = "Not found", response = Error404NGAIS.class),
        @ApiResponse(code = 405, message = "Method Not Allowed", response = Error405NGAIS.class),
        @ApiResponse(code = 406, message = "Not Acceptable", response = Error406NGAIS.class),
        @ApiResponse(code = 408, message = "Request Timeout"),
        @ApiResponse(code = 415, message = "Unsupported Media Type"),
        @ApiResponse(code = 429, message = "Too Many Requests", response = Error429NGAIS.class),
        @ApiResponse(code = 500, message = "Internal Server Error"),
        @ApiResponse(code = 503, message = "Service Unavailable") })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-GTW-Bank-Code", value = "Bank code of bank to which the request addressed", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "X-Request-ID", value = "ID of the request, unique to the call, as determined by the initiating party.", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "Consent-ID", value = "This then contains the consentId of the related AIS consent, which was performed prior to this payment initiation. ", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "Digest", value = "Is contained if and only if the \"Signature\" element is contained in the header of the request.", dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "Signature", value = "A signature of the request by the TPP on application level. This might be mandated by ASPSP. ", dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "TPP-Signature-Certificate", value = "The certificate used for signing the request, in base64 encoding.  Must be contained if a signature is contained. ", dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "PSU-IP-Address", value = "The forwarded IP Address header field consists of the corresponding HTTP request  IP Address field between PSU and TPP.  It shall be contained if and only if this request was actively initiated by the PSU. ", dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "PSU-IP-Port", value = "The forwarded IP Port header field consists of the corresponding HTTP request IP Port field between PSU and TPP, if available. ", dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "PSU-Accept", value = "The forwarded IP Accept header fields consist of the corresponding HTTP request Accept header fields between PSU and TPP, if available. ", dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "PSU-Accept-Charset", value = "The forwarded IP Accept header fields consist of the corresponding HTTP request Accept header fields between PSU and TPP, if available. ", dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "PSU-Accept-Encoding", value = "The forwarded IP Accept header fields consist of the corresponding HTTP request Accept header fields between PSU and TPP, if available. ", dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "PSU-Accept-Language", value = "The forwarded IP Accept header fields consist of the corresponding HTTP request Accept header fields between PSU and TPP, if available. ", dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "PSU-User-Agent", value = "The forwarded Agent header field of the HTTP request between PSU and TPP, if available. ", dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "PSU-Http-Method", value = "HTTP method used at the PSU ? TPP interface, if available. Valid values are: * GET * POST * PUT * PATCH * DELETE ", allowableValues = "GET, POST, PUT, PATCH, DELETE", dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "PSU-Device-ID", value = "UUID (Universally Unique Identifier) for a device, which is used by the PSU, if available. UUID identifies either a device or a device dependant application installation. In case of an installation identification this ID need to be unaltered until removal from device. ", dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "PSU-Geo-Location", value = "The forwarded Geo Location of the corresponding http request between PSU and TPP if available. ", dataType = "string", paramType = "header")
    })
    @RequestMapping(value = "/v1/accounts",
        produces = { "application/json", "application/problem+json" },
        method = RequestMethod.GET)
    ResponseEntity<AccountListTO> getAccountList(
            @ApiParam(value = "If contained, this function reads the list of accessible payment accounts including the booking balance,  if granted by the PSU in the related consent and available by the ASPSP.  This parameter might be ignored by the ASPSP.  ")
            @Valid
            @RequestParam(value = "withBalance", required = false) Boolean withBalance,
            @RequestHeader Map<String, String> headers);

    @ApiOperation(value = "Read Balance", nickname = "getBalances", notes = "Reads account data from a given account addressed by \"account-id\".   **Remark:** This account-id can be a tokenised identification due to data protection reason since the path  information might be logged on intermediary servers within the ASPSP sphere.  This account-id then can be retrieved by the \"GET Account List\" call.  The account-id is constant at least throughout the lifecycle of a given consent. ", response = ReadAccountBalanceResponse200.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = ReadAccountBalanceResponse200.class),
        @ApiResponse(code = 400, message = "Bad Request", response = Error400NGAIS.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = Error401NGAIS.class),
        @ApiResponse(code = 403, message = "Forbidden", response = Error403NGAIS.class),
        @ApiResponse(code = 404, message = "Not found", response = Error404NGAIS.class),
        @ApiResponse(code = 405, message = "Method Not Allowed", response = Error405NGAIS.class),
        @ApiResponse(code = 406, message = "Not Acceptable", response = Error406NGAIS.class),
        @ApiResponse(code = 408, message = "Request Timeout"),
        @ApiResponse(code = 415, message = "Unsupported Media Type"),
        @ApiResponse(code = 429, message = "Too Many Requests", response = Error429NGAIS.class),
        @ApiResponse(code = 500, message = "Internal Server Error"),
        @ApiResponse(code = 503, message = "Service Unavailable") })
    @RequestMapping(value = "/v1/accounts/{account-id}/balances",
        produces = { "application/json", "application/problem+json" },
        method = RequestMethod.GET)
    default ResponseEntity<ReadAccountBalanceResponse200> _getBalances(@ApiParam(value = "This identification is denoting the addressed account.  The account-id is retrieved by using a \"Read Account List\" call.  The account-id is the \"id\" attribute of the account structure.  Its value is constant at least throughout the lifecycle of a given consent. ", required = true) @PathVariable("account-id") String accountId, @ApiParam(value = "ID of the request, unique to the call, as determined by the initiating party.", required = true) @RequestHeader(value = "X-Request-ID", required = true) UUID xRequestID, @ApiParam(value = "This then contains the consentId of the related AIS consent, which was performed prior to this payment initiation. ", required = true) @RequestHeader(value = "Consent-ID", required = true) String consentID, @ApiParam(value = "Is contained if and only if the \"Signature\" element is contained in the header of the request.") @RequestHeader(value = "Digest", required = false) String digest, @ApiParam(value = "A signature of the request by the TPP on application level. This might be mandated by ASPSP. ") @RequestHeader(value = "Signature", required = false) String signature, @ApiParam(value = "The certificate used for signing the request, in base64 encoding.  Must be contained if a signature is contained. ") @RequestHeader(value = "TPP-Signature-Certificate", required = false) byte[] tpPSignatureCertificate, @ApiParam(value = "The forwarded IP Address header field consists of the corresponding HTTP request  IP Address field between PSU and TPP.  It shall be contained if and only if this request was actively initiated by the PSU. ") @RequestHeader(value = "PSU-IP-Address", required = false) String psUIPAddress, @ApiParam(value = "The forwarded IP Port header field consists of the corresponding HTTP request IP Port field between PSU and TPP, if available. ") @RequestHeader(value = "PSU-IP-Port", required = false) String psUIPPort, @ApiParam(value = "The forwarded IP Accept header fields consist of the corresponding HTTP request Accept header fields between PSU and TPP, if available. ") @RequestHeader(value = "PSU-Accept", required = false) String psUAccept, @ApiParam(value = "The forwarded IP Accept header fields consist of the corresponding HTTP request Accept header fields between PSU and TPP, if available. ") @RequestHeader(value = "PSU-Accept-Charset", required = false) String psUAcceptCharset, @ApiParam(value = "The forwarded IP Accept header fields consist of the corresponding HTTP request Accept header fields between PSU and TPP, if available. ") @RequestHeader(value = "PSU-Accept-Encoding", required = false) String psUAcceptEncoding, @ApiParam(value = "The forwarded IP Accept header fields consist of the corresponding HTTP request Accept header fields between PSU and TPP, if available. ") @RequestHeader(value = "PSU-Accept-Language", required = false) String psUAcceptLanguage, @ApiParam(value = "The forwarded Agent header field of the HTTP request between PSU and TPP, if available. ") @RequestHeader(value = "PSU-User-Agent", required = false) String psUUserAgent, @ApiParam(value = "HTTP method used at the PSU ? TPP interface, if available. Valid values are: * GET * POST * PUT * PATCH * DELETE ", allowableValues = "GET, POST, PUT, PATCH, DELETE") @RequestHeader(value = "PSU-Http-Method", required = false) String psUHttpMethod, @ApiParam(value = "UUID (Universally Unique Identifier) for a device, which is used by the PSU, if available. UUID identifies either a device or a device dependant application installation. In case of an installation identification this ID need to be unaltered until removal from device. ") @RequestHeader(value = "PSU-Device-ID", required = false) UUID psUDeviceID, @ApiParam(value = "The forwarded Geo Location of the corresponding http request between PSU and TPP if available. ") @RequestHeader(value = "PSU-Geo-Location", required = false) String psUGeoLocation) {
        return getBalances(accountId, xRequestID, consentID, digest, signature, tpPSignatureCertificate, psUIPAddress, psUIPPort, psUAccept, psUAcceptCharset, psUAcceptEncoding, psUAcceptLanguage, psUUserAgent, psUHttpMethod, psUDeviceID, psUGeoLocation);
    }

    // Override this method
    default ResponseEntity<ReadAccountBalanceResponse200> getBalances(String accountId, UUID xRequestID, String consentID, String digest, String signature, byte[] tpPSignatureCertificate, String psUIPAddress, String psUIPPort, String psUAccept, String psUAcceptCharset, String psUAcceptEncoding, String psUAcceptLanguage, String psUUserAgent, String psUHttpMethod, UUID psUDeviceID, String psUGeoLocation) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @ApiOperation(value = "Read Transaction Details", nickname = "getTransactionDetails", notes = "Reads transaction details from a given transaction addressed by \"resourceId\" on a given account addressed by \"account-id\".  This call is only available on transactions as reported in a JSON format.  **Remark:** Please note that the PATH might be already given in detail by the corresponding entry of the response of the  \"Read Transaction List\" call within the _links subfield. ", response = TransactionDetails.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = TransactionDetails.class),
        @ApiResponse(code = 400, message = "Bad Request", response = Error400NGAIS.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = Error401NGAIS.class),
        @ApiResponse(code = 403, message = "Forbidden", response = Error403NGAIS.class),
        @ApiResponse(code = 404, message = "Not found", response = Error404NGAIS.class),
        @ApiResponse(code = 405, message = "Method Not Allowed", response = Error405NGAIS.class),
        @ApiResponse(code = 406, message = "Not Acceptable", response = Error406NGAIS.class),
        @ApiResponse(code = 408, message = "Request Timeout"),
        @ApiResponse(code = 415, message = "Unsupported Media Type"),
        @ApiResponse(code = 429, message = "Too Many Requests", response = Error429NGAIS.class),
        @ApiResponse(code = 500, message = "Internal Server Error"),
        @ApiResponse(code = 503, message = "Service Unavailable") })
    @RequestMapping(value = "/v1/accounts/{account-id}/transactions/{resourceId}",
        produces = { "application/json", "application/problem+json" },
        method = RequestMethod.GET)
    default ResponseEntity<TransactionDetails> _getTransactionDetails(@ApiParam(value = "This identification is denoting the addressed account.  The account-id is retrieved by using a \"Read Account List\" call.  The account-id is the \"id\" attribute of the account structure.  Its value is constant at least throughout the lifecycle of a given consent. ", required = true) @PathVariable("account-id") String accountId, @ApiParam(value = "This identification is given by the attribute resourceId of the corresponding entry of a transaction list. ", required = true) @PathVariable("resourceId") String resourceId, @ApiParam(value = "ID of the request, unique to the call, as determined by the initiating party.", required = true) @RequestHeader(value = "X-Request-ID", required = true) UUID xRequestID, @ApiParam(value = "This then contains the consentId of the related AIS consent, which was performed prior to this payment initiation. ", required = true) @RequestHeader(value = "Consent-ID", required = true) String consentID, @ApiParam(value = "Is contained if and only if the \"Signature\" element is contained in the header of the request.") @RequestHeader(value = "Digest", required = false) String digest, @ApiParam(value = "A signature of the request by the TPP on application level. This might be mandated by ASPSP. ") @RequestHeader(value = "Signature", required = false) String signature, @ApiParam(value = "The certificate used for signing the request, in base64 encoding.  Must be contained if a signature is contained. ") @RequestHeader(value = "TPP-Signature-Certificate", required = false) byte[] tpPSignatureCertificate, @ApiParam(value = "The forwarded IP Address header field consists of the corresponding HTTP request  IP Address field between PSU and TPP.  It shall be contained if and only if this request was actively initiated by the PSU. ") @RequestHeader(value = "PSU-IP-Address", required = false) String psUIPAddress, @ApiParam(value = "The forwarded IP Port header field consists of the corresponding HTTP request IP Port field between PSU and TPP, if available. ") @RequestHeader(value = "PSU-IP-Port", required = false) String psUIPPort, @ApiParam(value = "The forwarded IP Accept header fields consist of the corresponding HTTP request Accept header fields between PSU and TPP, if available. ") @RequestHeader(value = "PSU-Accept", required = false) String psUAccept, @ApiParam(value = "The forwarded IP Accept header fields consist of the corresponding HTTP request Accept header fields between PSU and TPP, if available. ") @RequestHeader(value = "PSU-Accept-Charset", required = false) String psUAcceptCharset, @ApiParam(value = "The forwarded IP Accept header fields consist of the corresponding HTTP request Accept header fields between PSU and TPP, if available. ") @RequestHeader(value = "PSU-Accept-Encoding", required = false) String psUAcceptEncoding, @ApiParam(value = "The forwarded IP Accept header fields consist of the corresponding HTTP request Accept header fields between PSU and TPP, if available. ") @RequestHeader(value = "PSU-Accept-Language", required = false) String psUAcceptLanguage, @ApiParam(value = "The forwarded Agent header field of the HTTP request between PSU and TPP, if available. ") @RequestHeader(value = "PSU-User-Agent", required = false) String psUUserAgent, @ApiParam(value = "HTTP method used at the PSU ? TPP interface, if available. Valid values are: * GET * POST * PUT * PATCH * DELETE ", allowableValues = "GET, POST, PUT, PATCH, DELETE") @RequestHeader(value = "PSU-Http-Method", required = false) String psUHttpMethod, @ApiParam(value = "UUID (Universally Unique Identifier) for a device, which is used by the PSU, if available. UUID identifies either a device or a device dependant application installation. In case of an installation identification this ID need to be unaltered until removal from device. ") @RequestHeader(value = "PSU-Device-ID", required = false) UUID psUDeviceID, @ApiParam(value = "The forwarded Geo Location of the corresponding http request between PSU and TPP if available. ") @RequestHeader(value = "PSU-Geo-Location", required = false) String psUGeoLocation) {
        return getTransactionDetails(accountId, resourceId, xRequestID, consentID, digest, signature, tpPSignatureCertificate, psUIPAddress, psUIPPort, psUAccept, psUAcceptCharset, psUAcceptEncoding, psUAcceptLanguage, psUUserAgent, psUHttpMethod, psUDeviceID, psUGeoLocation);
    }

    // Override this method
    default ResponseEntity<TransactionDetails> getTransactionDetails(String accountId, String resourceId, UUID xRequestID, String consentID, String digest, String signature, byte[] tpPSignatureCertificate, String psUIPAddress, String psUIPPort, String psUAccept, String psUAcceptCharset, String psUAcceptEncoding, String psUAcceptLanguage, String psUUserAgent, String psUHttpMethod, UUID psUDeviceID, String psUGeoLocation) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @ApiOperation(value = "Read transaction list of an account", nickname = "getTransactionList", notes = "Read transaction reports or transaction lists of a given account ddressed by \"account-id\", depending on the steering parameter  \"bookingStatus\" together with balances.  For a given account, additional parameters are e.g. the attributes \"dateFrom\" and \"dateTo\".  The ASPSP might add balance information, if transaction lists without balances are not supported. ", response = TransactionsResponse200Json.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = TransactionsResponse200Json.class),
        @ApiResponse(code = 400, message = "Bad Request", response = Error400NGAIS.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = Error401NGAIS.class),
        @ApiResponse(code = 403, message = "Forbidden", response = Error403NGAIS.class),
        @ApiResponse(code = 404, message = "Not found", response = Error404NGAIS.class),
        @ApiResponse(code = 405, message = "Method Not Allowed", response = Error405NGAIS.class),
        @ApiResponse(code = 406, message = "Not Acceptable", response = Error406NGAIS.class),
        @ApiResponse(code = 408, message = "Request Timeout"),
        @ApiResponse(code = 415, message = "Unsupported Media Type"),
        @ApiResponse(code = 429, message = "Too Many Requests", response = Error429NGAIS.class),
        @ApiResponse(code = 500, message = "Internal Server Error"),
        @ApiResponse(code = 503, message = "Service Unavailable") })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-GTW-Bank-Code", value = "Bank code of bank to which the request addressed", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "X-Request-ID", value = "ID of the request, unique to the call, as determined by the initiating party.", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "Consent-ID", value = "This then contains the consentId of the related AIS consent, which was performed prior to this payment initiation. ", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "Digest", value = "Is contained if and only if the \"Signature\" element is contained in the header of the request.", dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "Signature", value = "A signature of the request by the TPP on application level. This might be mandated by ASPSP. ", dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "TPP-Signature-Certificate", value = "The certificate used for signing the request, in base64 encoding.  Must be contained if a signature is contained. ", dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "PSU-IP-Address", value = "The forwarded IP Address header field consists of the corresponding HTTP request  IP Address field between PSU and TPP.  It shall be contained if and only if this request was actively initiated by the PSU. ", dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "PSU-IP-Port", value = "The forwarded IP Port header field consists of the corresponding HTTP request IP Port field between PSU and TPP, if available. ", dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "PSU-Accept", value = "The forwarded IP Accept header fields consist of the corresponding HTTP request Accept header fields between PSU and TPP, if available. ", dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "PSU-Accept-Charset", value = "The forwarded IP Accept header fields consist of the corresponding HTTP request Accept header fields between PSU and TPP, if available. ", dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "PSU-Accept-Encoding", value = "The forwarded IP Accept header fields consist of the corresponding HTTP request Accept header fields between PSU and TPP, if available. ", dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "PSU-Accept-Language", value = "The forwarded IP Accept header fields consist of the corresponding HTTP request Accept header fields between PSU and TPP, if available. ", dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "PSU-User-Agent", value = "The forwarded Agent header field of the HTTP request between PSU and TPP, if available. ", dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "PSU-Http-Method", value = "HTTP method used at the PSU ? TPP interface, if available. Valid values are: * GET * POST * PUT * PATCH * DELETE ", allowableValues = "GET, POST, PUT, PATCH, DELETE", dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "PSU-Device-ID", value = "UUID (Universally Unique Identifier) for a device, which is used by the PSU, if available. UUID identifies either a device or a device dependant application installation. In case of an installation identification this ID need to be unaltered until removal from device. ", dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "PSU-Geo-Location", value = "The forwarded Geo Location of the corresponding http request between PSU and TPP if available. ", dataType = "string", paramType = "header")
    })
    @RequestMapping(value = "/v1/accounts/{account-id}/transactions",
        produces = {"application/json", "application/xml", "text/plain", "application/problem+json"},
        method = RequestMethod.GET)
    ResponseEntity<?> getTransactionList(
            @ApiParam(value = "This identification is denoting the addressed account.  The account-id is retrieved by using a \"Read Account List\" call.  The account-id is the \"id\" attribute of the account structure.  Its value is constant at least throughout the lifecycle of a given consent. ", required = true)
            @PathVariable("account-id") String accountId,
            @ApiParam(value = "Permitted codes are    * \"booked\",   * \"pending\" and    * \"both\" \"booked\" shall be supported by the ASPSP. To support the \"pending\" and \"both\" feature is optional for the ASPSP,  Error code if not supported in the online banking frontend ", required = true, allowableValues = "booked, pending, both")
            @RequestParam(value = "bookingStatus", required = true) String bookingStatus,
            @ApiParam(value = "Conditional: Starting date (inclusive the date dateFrom) of the transaction list, mandated if no delta access is required.  For booked transactions, the relevant date is the booking date.   For pending transactions, the relevant date is the entry date, which may not be transparent  neither in this API nor other channels of the ASPSP. ")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @RequestParam(value = "dateFrom", required = false) LocalDate dateFrom,
            @ApiParam(value = "End date (inclusive the data dateTo) of the transaction list, default is \"now\" if not given.   Might be ignored if a delta function is used.  For booked transactions, the relevant date is the booking date.   For pending transactions, the relevant date is the entry date, which may not be transparent  neither in this API nor other channels of the ASPSP. ")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @RequestParam(value = "dateTo", required = false) LocalDate dateTo,
            @ApiParam(value = "This data attribute is indicating that the AISP is in favour to get all transactions after  the transaction with identification entryReferenceFrom alternatively to the above defined period.  This is a implementation of a delta access.  If this data element is contained, the entries \"dateFrom\" and \"dateTo\" might be ignored by the ASPSP  if a delta report is supported.  Optional if supported by API provider. ")
            @RequestParam(value = "entryReferenceFrom", required = false) String entryReferenceFrom,
            @ApiParam(value = "This data attribute is indicating that the AISP is in favour to get all transactions after the last report access for this PSU on the addressed account. This is another implementation of a delta access-report. This delta indicator might be rejected by the ASPSP if this function is not supported. Optional if supported by API provider")
            @RequestParam(value = "deltaList", required = false) Boolean deltaList,
            @ApiParam(value = "If contained, this function reads the list of accessible payment accounts including the booking balance,  if granted by the PSU in the related consent and available by the ASPSP.  This parameter might be ignored by the ASPSP.  ")
            @RequestParam(value = "withBalance", required = false) Boolean withBalance,
            @RequestHeader Map<String, String> headers);

    @ApiOperation(value = "Read Account Details", nickname = "readAccountDetails", notes = "Reads details about an account, with balances where required.  It is assumed that a consent of the PSU to  this access is already given and stored on the ASPSP system.  The addressed details of this account depends then on the stored consent addressed by consentId,  respectively the OAuth2 access token.  **NOTE:** The account-id can represent a multicurrency account.  In this case the currency code is set to \"XXX\".  Give detailed information about the addressed account.  Give detailed information about the addressed account together with balance information ", response = AccountDetailsTO.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = AccountDetailsTO.class),
        @ApiResponse(code = 400, message = "Bad Request", response = Error400NGAIS.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = Error401NGAIS.class),
        @ApiResponse(code = 403, message = "Forbidden", response = Error403NGAIS.class),
        @ApiResponse(code = 404, message = "Not found", response = Error404NGAIS.class),
        @ApiResponse(code = 405, message = "Method Not Allowed", response = Error405NGAIS.class),
        @ApiResponse(code = 406, message = "Not Acceptable", response = Error406NGAIS.class),
        @ApiResponse(code = 408, message = "Request Timeout"),
        @ApiResponse(code = 415, message = "Unsupported Media Type"),
        @ApiResponse(code = 429, message = "Too Many Requests", response = Error429NGAIS.class),
        @ApiResponse(code = 500, message = "Internal Server Error"),
        @ApiResponse(code = 503, message = "Service Unavailable") })
    @RequestMapping(value = "/v1/accounts/{account-id}",
        produces = { "application/json", "application/problem+json" },
        method = RequestMethod.GET)
    default ResponseEntity<AccountDetailsTO> _readAccountDetails(@ApiParam(value = "This identification is denoting the addressed account.  The account-id is retrieved by using a \"Read Account List\" call.  The account-id is the \"id\" attribute of the account structure.  Its value is constant at least throughout the lifecycle of a given consent. ", required = true) @PathVariable("account-id") String accountId, @ApiParam(value = "ID of the request, unique to the call, as determined by the initiating party.", required = true) @RequestHeader(value = "X-Request-ID", required = true) UUID xRequestID, @ApiParam(value = "This then contains the consentId of the related AIS consent, which was performed prior to this payment initiation. ", required = true) @RequestHeader(value = "Consent-ID", required = true) String consentID, @ApiParam(value = "If contained, this function reads the list of accessible payment accounts including the booking balance,  if granted by the PSU in the related consent and available by the ASPSP.  This parameter might be ignored by the ASPSP.  ") @Valid @RequestParam(value = "withBalance", required = false) Boolean withBalance, @ApiParam(value = "Is contained if and only if the \"Signature\" element is contained in the header of the request.") @RequestHeader(value = "Digest", required = false) String digest, @ApiParam(value = "A signature of the request by the TPP on application level. This might be mandated by ASPSP. ") @RequestHeader(value = "Signature", required = false) String signature, @ApiParam(value = "The certificate used for signing the request, in base64 encoding.  Must be contained if a signature is contained. ") @RequestHeader(value = "TPP-Signature-Certificate", required = false) byte[] tpPSignatureCertificate, @ApiParam(value = "The forwarded IP Address header field consists of the corresponding HTTP request  IP Address field between PSU and TPP.  It shall be contained if and only if this request was actively initiated by the PSU. ") @RequestHeader(value = "PSU-IP-Address", required = false) String psUIPAddress, @ApiParam(value = "The forwarded IP Port header field consists of the corresponding HTTP request IP Port field between PSU and TPP, if available. ") @RequestHeader(value = "PSU-IP-Port", required = false) String psUIPPort, @ApiParam(value = "The forwarded IP Accept header fields consist of the corresponding HTTP request Accept header fields between PSU and TPP, if available. ") @RequestHeader(value = "PSU-Accept", required = false) String psUAccept, @ApiParam(value = "The forwarded IP Accept header fields consist of the corresponding HTTP request Accept header fields between PSU and TPP, if available. ") @RequestHeader(value = "PSU-Accept-Charset", required = false) String psUAcceptCharset, @ApiParam(value = "The forwarded IP Accept header fields consist of the corresponding HTTP request Accept header fields between PSU and TPP, if available. ") @RequestHeader(value = "PSU-Accept-Encoding", required = false) String psUAcceptEncoding, @ApiParam(value = "The forwarded IP Accept header fields consist of the corresponding HTTP request Accept header fields between PSU and TPP, if available. ") @RequestHeader(value = "PSU-Accept-Language", required = false) String psUAcceptLanguage, @ApiParam(value = "The forwarded Agent header field of the HTTP request between PSU and TPP, if available. ") @RequestHeader(value = "PSU-User-Agent", required = false) String psUUserAgent, @ApiParam(value = "HTTP method used at the PSU ? TPP interface, if available. Valid values are: * GET * POST * PUT * PATCH * DELETE ", allowableValues = "GET, POST, PUT, PATCH, DELETE") @RequestHeader(value = "PSU-Http-Method", required = false) String psUHttpMethod, @ApiParam(value = "UUID (Universally Unique Identifier) for a device, which is used by the PSU, if available. UUID identifies either a device or a device dependant application installation. In case of an installation identification this ID need to be unaltered until removal from device. ") @RequestHeader(value = "PSU-Device-ID", required = false) UUID psUDeviceID, @ApiParam(value = "The forwarded Geo Location of the corresponding http request between PSU and TPP if available. ") @RequestHeader(value = "PSU-Geo-Location", required = false) String psUGeoLocation) {
        return readAccountDetails(accountId, xRequestID, consentID, withBalance, digest, signature, tpPSignatureCertificate, psUIPAddress, psUIPPort, psUAccept, psUAcceptCharset, psUAcceptEncoding, psUAcceptLanguage, psUUserAgent, psUHttpMethod, psUDeviceID, psUGeoLocation);
    }

    // Override this method
    default ResponseEntity<AccountDetailsTO> readAccountDetails(String accountId, UUID xRequestID, String consentID, Boolean withBalance, String digest, String signature, byte[] tpPSignatureCertificate, String psUIPAddress, String psUIPPort, String psUAccept, String psUAcceptCharset, String psUAcceptEncoding, String psUAcceptLanguage, String psUUserAgent, String psUHttpMethod, UUID psUDeviceID, String psUGeoLocation) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


}


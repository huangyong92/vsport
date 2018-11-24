package sport.handle;

//@ControllerAdvice
//public class BadRequestExceptionHandler extends ResponseEntityExceptionHandler {
//
////    @Override
////    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
////        String message = "";
////        BindingResult result = ex.getBindingResult();
////        //组装校验错误信息
////        if(result.hasErrors()){
////            List<ObjectError> list = result.getAllErrors();
////            StringBuffer errorMsgBuffer = new StringBuffer();
////            for(ObjectError error:list){
////                if (error instanceof FieldError) {
////                    FieldError errorMessage = (FieldError) error;
////                    errorMsgBuffer = errorMsgBuffer.append(errorMessage.getDefaultMessage()).append(",");
////                }
////            }
////            //返回信息格式处理
////            message = errorMsgBuffer.toString().substring(0,errorMsgBuffer.length()-1);
////        }
////
////        ResultVo<String> errorMessage =  ResultUtil.error(message);
////        return new ResponseEntity<>("String", status);
////    }
//
//
//    @Override
//    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        return super.handleMissingServletRequestParameter(ex, headers, status, request);
//    }
//
//    public BadRequestExceptionHandler() {
//        super();
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        return super.handleHttpRequestMethodNotSupported(ex, headers, status, request);
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        return super.handleHttpMediaTypeNotSupported(ex, headers, status, request);
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        return super.handleHttpMediaTypeNotAcceptable(ex, headers, status, request);
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        return super.handleMissingPathVariable(ex, headers, status, request);
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        return super.handleServletRequestBindingException(ex, headers, status, request);
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        return super.handleConversionNotSupported(ex, headers, status, request);
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        return super.handleTypeMismatch(ex, headers, status, request);
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        return super.handleHttpMessageNotReadable(ex, headers, status, request);
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        return super.handleHttpMessageNotWritable(ex, headers, status, request);
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        return super.handleMethodArgumentNotValid(ex, headers, status, request);
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        return super.handleMissingServletRequestPart(ex, headers, status, request);
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        return super.handleBindException(ex, headers, status, request);
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        return super.handleNoHandlerFoundException(ex, headers, status, request);
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
//        return super.handleAsyncRequestTimeoutException(ex, headers, status, webRequest);
//    }

//    @Override
//    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        return super.handleExceptionInternal(ex, body, headers, status, request);
//    }
//}

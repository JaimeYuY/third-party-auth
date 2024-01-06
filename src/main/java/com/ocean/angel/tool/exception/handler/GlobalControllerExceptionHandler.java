package com.ocean.angel.tool.exception.handler;

import com.ocean.angel.tool.common.ActionResult;
import com.ocean.angel.tool.exception.BusinessException;
import com.ocean.angel.tool.exception.ErrorCodeException;
import com.ocean.angel.tool.constants.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.SystemException;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import java.io.IOException;
import java.sql.SQLException;


@Slf4j
@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(ErrorCodeException.class)
    @ResponseBody
    public ActionResult handleErrorCodeException(ErrorCodeException e) {

        Integer code = e.getErrorCode();
        String msg = e.getMessage();

        // 外部传入了消息描述，这个地方就不再覆盖了
        if (!StringUtils.hasLength(msg)) {
            msg = "服务器正忙，请稍后重试。";
        }

        if (e instanceof BusinessException) {
            log.error("Business exception : msg {} : code {}", msg, code);
        } else {
            log.error("ErrorCodeException exception : msg {}", msg, e);
        }

        return ActionResult.error(ResultCode.INTERNAL_SERVER_ERROR);
    }

    /**
     * 空指针
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public ActionResult nullPointerException(NullPointerException e) {
        log.error("发生空指针异常！原因是:", e);
        return ActionResult.error(ResultCode.INTERNAL_SERVER_ERROR);
    }

    /**
     * 类型强制转换异常
     */
    @ExceptionHandler(value = ClassCastException.class)
    @ResponseBody
    public ActionResult classCastException(ClassCastException e) {
        log.error("类型强制转换异常！原因是:", e);
        return ActionResult.error(ResultCode.INTERNAL_SERVER_ERROR);
    }

    /**
     * 数组下标越界异常
     */
    @ExceptionHandler(value = ArrayIndexOutOfBoundsException.class)
    @ResponseBody
    public ActionResult arrayIndexOutOfBoundsException(ArrayIndexOutOfBoundsException e) {
        log.error("数组下标越界异常！原因是:", e);
        return ActionResult.error(ResultCode.INTERNAL_SERVER_ERROR);
    }

    /**
     * 字符串转换为数字异常
     */
    @ExceptionHandler(value = NumberFormatException.class)
    @ResponseBody
    public ActionResult numberFormatException(NumberFormatException e) {
        log.error("字符串转换为数字异常！原因是:", e);
        return ActionResult.error(ResultCode.INTERNAL_SERVER_ERROR);
    }

    /**
     * 输入输出异常
     */
    @ExceptionHandler(value = IOException.class)
    @ResponseBody
    public ActionResult IOException(IOException e) {
        log.error("输入输出异常！原因是:", e);
        return ActionResult.error(ResultCode.INTERNAL_SERVER_ERROR);
    }

    /**
     * 方法未找到异常
     */
    @ExceptionHandler(value = NoSuchMethodException.class)
    @ResponseBody
    public ActionResult noSuchMethodException(NoSuchMethodException e) {
        log.error("方法未找到异常！原因是:", e);
        return ActionResult.error(ResultCode.INTERNAL_SERVER_ERROR);
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(value = SystemException.class)
    @ResponseBody
    public ActionResult systemException(SystemException e) {
        log.error("系统异常！原因是:", e);
        return ActionResult.error(ResultCode.INTERNAL_SERVER_ERROR);
    }

    /**
     * 非法参数异常
     */
    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseBody
    public ActionResult IllegalArgumentException(IllegalArgumentException e) {
        log.error("非法参数异常！原因是:", e);
        return ActionResult.error(ResultCode.BAD_REQUEST);
    }

    /**
     * 不支持的操作异常
     */
    @ExceptionHandler(value = UnsupportedOperationException.class)
    @ResponseBody
    public ActionResult unsupportedOperationException(UnsupportedOperationException e) {
        log.error("不支持的操作异常！原因是:", e);
        return ActionResult.error(ResultCode.BAD_REQUEST);
    }

    /**
     * 操作数据库异常
     */
    @ExceptionHandler(value = SQLException.class)
    @ResponseBody
    public ActionResult sqlException(SQLException e) {
        log.error("操作数据库异常！原因是:", e);
        return ActionResult.error(ResultCode.INTERNAL_SERVER_ERROR);
    }

    /**
     * 参数校验失败
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ActionResult handlerConstraintViolationException(BindException e) {
        log.error(e.getMessage(), e);
        if (!e.getAllErrors().isEmpty()) {
            return ActionResult.error(ResultCode.BAD_REQUEST);
        } else {
            return ActionResult.error(ResultCode.BAD_REQUEST);
        }

    }

    /**
     * 方法参数无效异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ActionResult handlerArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        if (!e.getBindingResult().getAllErrors().isEmpty()) {
            return ActionResult.error(ResultCode.BAD_REQUEST);
        } else {
            return ActionResult.error(ResultCode.BAD_REQUEST);
        }
    }

    /**
     * Internal Server Error 500
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ActionResult handlerException(Exception e) {
        log.error(e.getMessage(), e);
        return ActionResult.error(ResultCode.INTERNAL_SERVER_ERROR);
    }

    /**
     * 404异常
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ActionResult noHandlerFoundException(NoHandlerFoundException e) {
        log.error(e.getMessage(), e);
        return ActionResult.error(ResultCode.NOT_FOUND);
    }
}

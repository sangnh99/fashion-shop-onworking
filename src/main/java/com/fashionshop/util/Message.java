package com.fashionshop.util;

public enum Message {
    USER_NOT_FOUND(1, "Không tìm thấy tài khoản tương ứng"),
    ERROR_SENDING_EMAIL(2, "Error while sending email"),
    INVALID_TOKEN(3, "Token này đã hết thời gian sử dụng hoặc đã được truy cập"),
    CONTENT_EMAIL(4, "Kiểm tra trong email cá nhân của bạn."),
    CHANGE_PASS_SUCCESSFUL(5, "Thay đổi mật khẩu thành công"),
    SUBJECT_MAIL(6, "Thay đổi mật khẩu Miniontmint");

    private String detail;
    private int code;

    Message(int code, String detail) {
        this.code = code;
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }

    public int getCode() {
        return code;
    }
}

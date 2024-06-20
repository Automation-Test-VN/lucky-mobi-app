package net.automobile.qa.ui;


import net.serenitybdd.screenplay.targets.Target;

public class RegisterUI {

    public static final Target REGISTER_FIELD = Target.the("{0} field")
            .locatedBy("//android.widget.TextView[@text=\"{0}\"]/preceding-sibling::android.widget.EditText");

    public static final Target USER_NAME = REGISTER_FIELD.of("Tên đăng nhập");
    public static final Target PASSWORD = REGISTER_FIELD.of("Mật khẩu");
    public static final Target CONFIRM_PWD = REGISTER_FIELD.of("Xác nhận mật khẩu");
    public static final Target PHONE_NUMBER = REGISTER_FIELD.of("Số điện thoại");

    public static final Target NEXT_BUTTON = Target.the("{0} field")
            .locatedBy("//android.widget.TextView[@text=\"TIẾP TỤC\"]/preceding-sibling::android.view.View");

    public static final Target LUCKY_NUMBER = Target.the("{0} field")
            .locatedBy("//android.widget.TextView[@text=\"{0}\"]/parent::android.view.ViewGroup");

    public static final Target DONE = LUCKY_NUMBER.of("HOÀN THÀNH");

    public static final Target TEXT = Target.the("{0} field")
            .locatedBy("//android.widget.TextView[@text=\"{0}\"]");

    //new UiSelector().text("TRANG CHỦ")
}

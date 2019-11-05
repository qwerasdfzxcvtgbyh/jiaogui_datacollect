package com.qmtec.common.status;

public enum StatusCodeEnum {
    Fail(-1,"失败"),
    ParamsIsEmpty(-1001,"参数不能为空"),
    NoPermission(-1002,"没有权限执行此操作"),
    ParamsInvalid(-1003,"无效的参数"),
    DuplicatedName(-1004,"重复的名称"),

    // 文件夹
    FolderNotEmpty(-2001,"文件夹不为空"),
    FolderInstanceNotNull(-2002,"folder对象不能为空"),
    UserIdInvalid(-2003,"用户ID错误"),
    FolderIdInvalid(-2004,"文件夹的ID错误"),
    FolderNameExists(-2006, "存在相同名称的文件夹"),
    FolderNotExists(-2007, "文件夹不存在"),
    FolderToggleFailed(-2018, "切换文件夹操作失败"),

    // 数据源
    ConnectionFailed(-2104,"不能连接目标库"),
    DatasourceIsNotEmpty(-2101,"该数据源下面还有数据模型"),
    DatasourceDeleteFailed(-2102,"删除数据源失败"),
    DatasourceIdIsEmpty(-2103,"数据源ID不能为空"),
    DatasourceIsEmpty(-2104,"数据源不存在"),
    DatasourceUpdateTableFail(-2105,"更新数据源的表失败"),
    DatasourceSaveFail(-2106,"添加数据源失败"),
    TableIsEmpty(-2107,"该表没有数据"),

    // 数据模型
    DatasetIsEmpty(-2201, "数据模型不存在"),
    ModelNameDuplicated(-2202,"不能存在相同的数据模型名称"),
    ModelNameIsEmpty(-2203,"数据模型名称不能为空"),
    DatasetConfigIsEmpty(-2204,"没有设置数据模型"),
    DatasetIsUsing(-2205,"该数据模型正在使用中"),

    // 数据表
    DatatableNotExists(-2301, "数据表不存在"),
    DatatableIdInvalid(-2302,"指定的数据表ID不能为0"),
    DatatableSaveFailed(-2303,"保存数据表时失败"),
    DatatableDeleteFailed(-2304,"删除数据表时失败"),
    DatatableIsEmpty(-2305,"报表没有数据返回"),
    DatatableConfigInvalid(-2306,"报表配置错误"),

    // 图表
    WidgetCountsInvalid(-2401, "要操作的报表数量错误"),
    WidgetDuplicatedName(-2402,"重复的图表名称"),
    WidgetUpdateError(-2403,"更新图表错误，该图表不属于当前用户"),
    WidgetSaveError(-2404,"保存图表失败"),
    DimensionStringEmpty(-2405, "生成附加纬度的字符串为空"),
    ExpressionStringEmpty(-2406, "生成附加数值的字符串为空"),
    WidgetIdInvalid(-2407, "要修改的图表ID不能为空"),

    // 仪表盘
    DashDuplicatedName(-4001,"重复的仪表盘名称"),
    DashSetTopError(-4002,"不能设置多个驾驶仓"),




    //用户
    OrganizationExistFailed(-5002,"编码名称已经存在"),
    LoginNameExistFailed(-5003,"该账号已经被使用了"),
    UserEmailExistFailed(-5004,"该邮箱已经注册过了"),
    UserEmailFormatFailed(-5007,"邮箱格式不正确"),
    TwicePwdImparityFailed(-5008, "两次密码输入不一致"),
    OldPwdMatchingFailed(-5011, "原密码输入不正确"),
    NewOldPwdIdenticalFailed(-5009, "新旧密码一致"),
    UserTokenExpired(-5009,"登录凭证过期, 需要重新登录"),
    NameOrPasswordIsEmpty(-5010,"登录名或者密码为空"),
    UserNotExists(-5011,"用户不存在"),

    // 权限相关
    RoleIsExists(-7001,"角色已存在"),
    RoleNotExists(-7002,"角色不存在"),
    RoleIsUsing(-7003,"角色正在使用中"),
    AppNotExists(-7004,"应用不存在"),
    AppIsExists(-7005,"应用已存在"),
    UserRoleGroupNotExistFailed(-7006,"用户角色没有加入用户角色组"),
    RoleNameDuplicated(-7007, "角色名已经存在"),
    NoResourcePermission(-7008,"没有访问指定资源的权限"),


    // 上传excel
    FileSaveError(-6001,"保存文件失败"),
    FileFormatInvalid(-6002,"文件格式错误"),
    FileIsEmpty(-6003,"找不到上传文件"),
    ExcelNameIsEmpty(-6004,"Excel的名称为空"),
    ExcelConfigError(-6005,"Excel的配置文件错误"),
    FileTransforming(-6006,"文件正在转换中"),
    PreviewDataInvalid(-6007,"生成的预览数据无效，需要检查数据是否上传正确"),
    TableHeadAbsent(-6008,"表头字段与要求字段不符"),


    ProjectIdDuplicated(-8001,"项目ID重复"),
    PorjectNumError(-8002,"创建虚拟项目时，原项目数量不足"),
    ProjectCreateFailed(-8003, "创建项目失败"),

    // 订阅
    CloudModelOrderDuplicated(-9001,"重复订阅"),

    // 数据表元信息
    SchemaColumnTransError(-10001,"列转换失败"),
    SchemaColumnIsEmpty(-10002,"列转换失败"),

    //函数查询
    FunctionIsEmpty(-11001,"函数查询为空"),


    Success(0,"成功");

    private int subCode;
    private String message;
    private String result;

    StatusCodeEnum(int subCode, String message){
        this.subCode = subCode;
        this.message = message;
    }

    public int getSubCode() {
        return subCode;
    }

    public String getMessage() {

        return message;
    }

    public String getResult(){
        return this.getClass().getSimpleName();
    }


    @Override
    public String toString() {
        return "{\"subCode\":" + subCode +", \"message\":\"" + message + "\",\"name\":\""+this.getResult()+"\"}";
    }
}

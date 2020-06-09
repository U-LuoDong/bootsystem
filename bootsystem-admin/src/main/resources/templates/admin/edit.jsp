<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<!DOCTYPE html>
<html><head>
        <meta charset="utf-8">
    <title>后台管理系统</title>

    <meta name="description" content="Dashboard">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!--Basic Styles-->
    <link href="/OnlineBookstore/static/admin/style/bootstrap.css" rel="stylesheet">
    <link href="/OnlineBookstore/static/admin/style/font-awesome.css" rel="stylesheet">
    <link href="/OnlineBookstore/static/admin/style/weather-icons.css" rel="stylesheet">

    <!--Beyond styles-->
    <link id="beyond-link" href="/OnlineBookstore/static/admin/style/beyond.css" rel="stylesheet" type="text/css">
    <link href="/OnlineBookstore/static/admin/style/demo.css" rel="stylesheet">
    <link href="/OnlineBookstore/static/admin/style/typicons.css" rel="stylesheet">
    <link href="/OnlineBookstore/static/admin/style/animate.css" rel="stylesheet">

</head>
<body>
    <!-- 头部 -->
    <%@ include file="../public/top.jsp" %>
    <!-- /头部 -->
    
    <div class="main-container container-fluid">
        <div class="page-container">
            <!-- Page Sidebar -->
            <%@ include file="../public/left.jsp" %>
            <!-- /Page Sidebar -->
            <!-- Page Content -->
            <div class="page-content">
                <!-- Page Breadcrumb -->
                <div class="page-breadcrumbs">
                    <ul class="breadcrumb">
                                        <li>
                        <a href="#">系统</a>
                    </li>
                                        <li>
                        <a href="/OnlineBookstore/AdminLst">管理员管理</a>
                    </li>
                                        <li class="active">修改管理员</li>
                                        </ul>
                </div>
                <!-- /Page Breadcrumb -->

                <!-- Page Body -->
                <div class="page-body">
                    
<div class="row">
    <div class="col-lg-12 col-sm-12 col-xs-12">
        <div class="widget">
            <div class="widget-header bordered-bottom bordered-blue">
                <span class="widget-caption">修改管理员</span>
            </div>
            <div class="widget-body">
                <div id="horizontal-form">
                    <form class="form-horizontal" role="form" action="/OnlineBookstore/AdminEditRes" method="post" >
                        <input type="hidden" name="id" value="${requestScope.admin.id}">
                        
                        <div class="form-group">
                            <label for="userName" class="col-sm-2 control-label no-padding-right">管理员名称</label>
                            <div class="col-sm-6">
                                <input class="form-control" value="${requestScope.admin.userName}"  placeholder="" name="userName"  required=""  type="text">
                            </div>
                            <p class="help-block col-sm-4 red">* 必填</p>
                        </div>
                        
                        <div class="form-group">
                            <label for="group_id" class="col-sm-2 control-label no-padding-right">管理员密码</label>
                            <div class="col-sm-6">
                                <input class="form-control"   placeholder="为空则不修改" name="password" value="" type="text">
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-default">保存信息</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

                </div>
                <!-- /Page Body -->
            </div>
            <!-- /Page Content -->
        </div>  
    </div>

        <!--Basic Scripts-->
    <script src="/OnlineBookstore/static/admin/style/bootstrap.js"></script>
    <!--Beyond Scripts-->
    <script src="/OnlineBookstore/static/admin/style/beyond.js"></script>

</body></html>html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="/apa/main.do">
                <div class="sidebar-brand-icon rotate-n-15">
                    <i class="fas fa-laugh-wink"></i>
                </div>
                <div class="sidebar-brand-text mx-3 hansans"> 아파! 어디가? </div>
            </a>

 			<!-- Divider -->
            <hr class="sidebar-divider my-0" >

            <!-- Nav Item - Dashboard -->
            <li class="nav-item active">
                <a class="nav-link" href="#" style="">
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span>마이페이지</span>
                </a>
            </li>	
            <!-- Nav Item - Dashboard -->
          	

            <!-- Divider -->
            <hr class="sidebar-divider">
            
            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="/apa/pharmacy/info/view.do?pharmacyId=${dto.pharmacyId}">
                    <i class="fas fa-fw fa-hospital-user"></i>
                    <span>내 정보</span>
                </a>
            </li>

            <!-- Nav Item - Utilities Collapse Menu -->
            <li class="nav-item">
            
                <a class="nav-link collapsed" href="/apa/pharmacy/opening/view.do?pharmacyId=${dto.pharmacyId}">
                    <i class="fas fa-fw fa-laptop-medical"></i> 
                    <span>운영 관리</span>
                    
                </a>
                
            </li>
            
            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="/apa/pharmacy/dispense/list.do?pharmacyId=${dto.pharmacyId}">
                    <i class="fas fa-fw fa-pills"></i>
                    <span>약 제조 관리</span>
                </a>
           
            
            <!-- Nav Item - Charts -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="/apa/pharmacy/entry/list.do?pharmacyId=${dto.pharmacyId}">
                    <i class="fas fa-fw fa-clinic-medical"></i>
                    <span>입점 관리</span></a>
            </li>

         
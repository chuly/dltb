<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="author" content="">
	<meta name="keywords" content="磁力链 磁力键 Magnet BTIH 电驴 ED2K KAD 种子 Torrent P2PSearch xunlei 迅雷 thunder QQ旋风 uTorrent BT 115 360 btdigg">
	<meta name="description" content="磁力集中营(www.xxxx.com)最稳定最好用的BT磁力搜索引擎。界面清爽、无弹出广告。搜索质量高，检索内容稳定增长。">	
    <link rel="icon" href="/favicon.ico">
    <title>${keyWord}-磁力湾(www.xxxx.com)-磁力链搜索</title>
    <link href="//cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link href="//cdn.bootcss.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="${base_addr}/static/css/v1.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/r29/html5.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script src="${base_addr}/static/js/jquery-2.2.0.min.js"></script>
</head>

<body> 
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <h1 class="text-center" id="sub-logo">
                <a href="/"><img src="${base_addr}/static/img/logo1.png" alt="LOGO">
                    <span>磁力资源库</span>
                </a>
            </h1>
        </div>

        <div class="col-md-3 col-sm-2"></div>
        <div class="col-md-6  col-sm-8">

            <form id="searchForm" action="${base_addr}/mag/search" method="post" accept-charset="utf-8">
			<input type="hidden" name="type" value="1" style="display:none;" />
			<input type="hidden" name="page" id="page" value="1" style="display:none;" />
            <div class="input-group">
                <input type="text" class="form-control" id="home-search-input" name="keyWord" autocomplete="off"
                       placeholder="" value="${keyWord?if_exists}">
                      <span class="input-group-btn">
                        <button class="btn btn-primary" type="submit"><i class="fa fa-search"></i>
                            搜索
                        </button>
                      </span>
            </div>
            </form>        </div>
        <div class="col-md-3  col-sm-2"></div>
    </div>
    <div class="row mt-20">
        <div class="col-xs-12">
            <div class="love_3"></div>        </div>
    </div>
    <div class="row">
        <div class="col-xs-12 result">

                                                <div class="clearfix">
                        <span class="pull-left" style="line-height: 32px;margin-right: 1em">排序:</span>
                        <div class="btn-group" role="group" aria-label="...">
                            <button type="submit" disabled name="order" value="time_new_old"
                                    class="btn btn-default active">
                                从新到旧
                            </button>
                        </div>
                    </div>
                
                                <ul class="list">
                                <#if magList??>
								<#list magList as s>
                                                                    <li>
                            <span>${s_index+1}</span>
                            <h2>
                                <a style="word-wrap: break-word" href="${base_addr}/mag/detail?type=${s.oriType?if_exists}&detailCode=${s.detailCode?if_exists}">${s.name?if_exists}</a>
                            </h2>
                            <p><i class="fa fa-floppy-o"></i> 大小: ${s.fileSize?if_exists}
                            	<i class="fa fa-files-o"></i> 日期: ${s.dateStr?if_exists}</p>
                        </li>
                                </#list>
								</#if>
                </ul>
        </div>
    </div>
                        <div class="row mt-20">
                <div class="col-xs-12">
                </div>
            </div>
        
        <div class="row">
            <div class="col-xs-12">
                <ul class="pager">
                                            <li class="disabled">
                            <button id="btnPrePage" class="btn btn-default" onclick="toPage(${page?if_exists}-1)" value="1"><i
                                    class="fa fa-angle-double-left"></i> 上一页
                            </button>
                        </li>
                    
                    <li>&nbsp; ${page?if_exists}/${totlePage?if_exists} &nbsp;</li>

                                            <li>
                            <button id="btnNextPage" class="btn btn-default" onclick="toPage(${page?if_exists}+1)" name="page" value="2">
                                下一页 <i class="fa fa-angle-double-right"></i></button>
                        </li>
                    
                </ul>
                
            </div>
        </div>
       </div>

<div class="footer">
    <span></span><span><a href ="http://iz1111111zs.co/"></a></span>    <p>&copy; 2017 磁力资源库 www.biubiuq.online</p>
</div>

</body>
<script>
	$(function (){
	    var page = '${page?if_exists}';
	    var totlePage = '${totlePage?if_exists}';
		if(page == 1){
			$('#btnPrePage').attr("disabled","disabled");
		}
		if(page == totlePage){
			$('#btnNextPage').attr("disabled","disabled");
		}
	})
	function toPage(page){
		$('#page').val(page);
		$('#searchForm').submit();
	}
</script>
</html>
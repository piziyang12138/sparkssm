<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/2
  Time: 8:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0">
    <title>我在简书</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/echarts.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/jquery.fullPage.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.fullPage.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/echarts-wordcloud.min.js"></script>
    <script>
        $(function(){
            $('#dowebok').fullpage({
                sectionsColor:'#666666'
            });
        });
    </script>

    <style>
        #head_pic{
            width: 168px;
            height: 168px;
        }
    </style>
</head>

<body>
<div id="dowebok">

    <div class="section" id="base_info">
        <div align="center" >
            <img id="head_pic" src="${user.headPic}">
            <p>您好！&nbsp;<strong style="color:#FA8072">${user.nickname}</strong></p>
            <p>截止至&nbsp;<b style="color:#FA8072">${user.latestTime}</b></p>
            <p>您在简书关注了<b style="color:#FA8072"><c:if test="${empty user.followingNum}">0</c:if>${user.followingNum}</b>个用户，
                拥有粉丝<b style="color:#FA8072"><c:if test="${empty user.followersNum}">0</c:if>${user.followersNum}</b>个。</p>
            <p>发表文章<b style="color:#FA8072"><c:if test="${empty user.articlesNum}">0</c:if>${user.articlesNum}</b>篇，
                写下文字<b style="color:#FA8072"><c:if test="${empty user.wordsNum}">0</c:if>${user.wordsNum}</b>个，
                文章收获喜欢<b style="color:#FA8072"><c:if test="${empty user.beLikedNum}">0</c:if>${user.beLikedNum}</b>个,
                喜欢文章<b style="color:#FA8072"><c:if test="${empty user.likenotescount}">0</c:if>${user.likenotescount}</b>篇</p>
            <p>关注专题<b style="color:#FA8072"><c:if test="${empty user.likecollscount}">0</c:if>${user.likecollscount}</b>个，
                关注文集<b style="color:#FA8072"><c:if test="${empty user.likenotebookscount}">0</c:if>${user.likenotebookscount}</b>个。</p>
            <p>发表评论<b style="color:#FA8072"><c:if test="${empty user.commentnotescount}">0</c:if>${user.commentnotescount}</b>次，
                点赞别人评论<b style="color:#FA8072"><c:if test="${empty user.likecommentcount}">0</c:if>${user.likecommentcount}</b>次。</p>
            <p>打赏文章<b style="color:#FA8072"><c:if test="${empty user.rewardnotescount}">0</c:if>${user.rewardnotescount}</b>次</p>
        </div>
    </div>

    <div class="section" id="firt_tag_time">
        <div align="center" >
            <h3>加入简书以来的第一次</h3><br><br>
            <p>您于<b style="color:#FA8072">${user.joinTime}</b> 注册，加入简书</p>
            <c:if test="${!empty secondPart.firstlikeuser}">
                <p>
                    <b style="color:#FA8072">${secondPart.firstlikeuser}</b>
                    &nbsp;
                    <a href="http://www.jianshu.com/u/{{ first_tag_time['first_like_user']['slug']|safe }}" target="_blank">第一次关注用户</a>
                </p>
            </c:if>
            <c:if test="${!empty secondPart.firstsharenote}">
                <p>
                    <b style="color:#FA8072">${secondPart.firstsharenote}</b>
                    &nbsp;
                    <a href="http://www.jianshu.com/p/{{ first_tag_time['first_share_note']['note_id']|safe }}" target="_blank">第一次发表文章</a>
                </p>
            </c:if>
            <c:if test="${!empty secondPart.firstlikenote}">
                <p>
                    <b style="color:#FA8072">${secondPart.firstlikenote}</b>
                    &nbsp;
                    <a href="http://www.jianshu.com/p/{{ first_tag_time['first_like_note']['note_id']|safe }}" target="_blank">第一次喜欢文章</a>
                </p>
            </c:if>

            <c:if test="${!empty secondPart.firstlikecolls}">
                <p>
                    <b style="color:#FA8072">${secondPart.firstlikecolls}</b>
                    &nbsp;
                    <a href="http://www.jianshu.com/c/{{ first_tag_time['first_like_coll']['coll_id']|safe }}" target="_blank">第一次关注专题</a>
                </p>
            </c:if>

            <c:if test="${!empty secondPart.firstlikenotebooks}">
                <p>
                    <b style="color:#FA8072">${secondPart.firstlikenotebooks}</b>
                    &nbsp;
                    <a href="http://www.jianshu.com/nb/{{ first_tag_time['first_like_nb']['notebook_id']|safe }}" target="_blank">第一次关注文集</a>
                </p>
            </c:if>
            <c:if test="${!empty secondPart.firstcommentnote}">
                <p>
                    <b style="color:#FA8072">${secondPart.firstcommentnote}</b>
                    &nbsp;第一次发表评论:
                    <a href="http://www.jianshu.com/p/{{ first_tag_time['first_comment']['note_id']|safe }}" target="_blank">我没抓</a>
                </p>
            </c:if>
            <c:if test="${!empty secondPart.firstlikecomment}">
                <p>
                    <b style="color:#FA8072">${secondPart.firstlikecomment}</b>
                    &nbsp;第一次赞了评论:
                    <a href="http://www.jianshu.com/p/{{ first_tag_time['first_like_comment']['note_id']|safe }}" target="_blank">我也没抓</a>
                </p>
            </c:if>
            <c:if test="${!empty secondPart.firstrewardnote}">
                <p>
                    <b style="color:#FA8072">${secondPart.firstrewardnote}</b>
                    &nbsp;
                    <a href="http://www.jianshu.com/p/{{ first_tag_time['first_reward_note']['note_id']|safe }}" target="_blank">第一次打赏文章</a>
                </p>
            </c:if>

        </div>
    </div>

    <div class="section" id="tags_percent">
        <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
        <div id="tags_chart" style="width:100%;height:600px;"></div>
        <script type="text/javascript">
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('tags_chart'));

            // 指定图表的配置项和数据

            var option = {
                title : {
                    text: '用户动态类型',
                    subtext: '数据来源: www.jianshu.com',
                    x:'center'
                },
                tooltip : {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                clockwise:false,
                legend: {
                    orient: 'vertical',
                    left: '10%',
                    // data: ['发表评论','喜欢文章']
                   data: ['发表评论','喜欢文章','赞赏文章','发表文章','关注用户','关注专题','点赞评论','关注文集']
                },
                color:['#FF6666','#EFE42A','#64BD3D','#EE9201','#29AAE3',
                    '#B74AE5','#0AAF9F','#E89589'],
                series : [
                    {
                        name: '动态类型',
                        type: 'pie',
                        radius : '75%',
                        center: ['50%', '60%'],
                        data:${jsonCateNum},
            itemStyle: {
                emphasis: {
                    shadowBlur: 100,
                            shadowOffsetX: 10,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
            }
            ]
            };

            //*必须，绑定图表自适应功能
            window.onresize = function () {
                myChart.resize();
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);

        </script>
    </div>

    <div class="section" id="all_month">
        <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
        <div id="monthline_chart" style="width:100%;height:600px;"></div>
        <script type="text/javascript">
            // 基于准备好的dom，初始化echarts实例
            var myChart1 = echarts.init(document.getElementById('monthline_chart'));

            // 指定图表的配置项和数据

            var option = {

                // Make gradient line here
                visualMap: {
                    show: false,
                    type: 'continuous',
                    seriesIndex: 0,
                    color:['red','orange','yellow','lightskyblue']
                },

                title: {
                    left: 'center',
                    text: '各个月份的动态次数',
                    subtext:'数据来源: www.jianshu.com'
                },
                tooltip: {
                    trigger: 'axis'
                },
                xAxis: {
                    data: ${months},
            name:'月份'
            },
            yAxis: {
                splitLine: {show: false},
                name:'动态次数'
            },
            grid: {
                bottom: '6%',
                        top: '10%'
            },
            series: {
                type: 'line',
                        showSymbol: false,
                        data: ${monthsfreq},
                markPoint : {
                    data : [
                        {type : 'max',
                            name: '最大值'
                        }
                    ]
                },
                markLine: {
                    data: [
                        {type: 'average', name: '平均值',
                            label: {
                                normal: {
                                    position: 'end',
                                    formatter: '月平均值:{c}'
                                }
                            }},
                        {type: 'max', name: '最大值',
                            label: {
                                normal: {
                                    position: 'end',
                                    formatter: '最大值'
                                }
                            }}
                    ]
                }
            }
            };
            //*必须，绑定图表自适应功能
            window.onresize = function () {
                myChart1.resize();
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart1.setOption(option);

        </script>
    </div>

    <div class="section" id="all_day">
        <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
        <div id="dayline_chart" style="width:100%;height:600px;"></div>
        <script type="text/javascript">
            // 基于准备好的dom，初始化echarts实例
            var myChart2 = echarts.init(document.getElementById('dayline_chart'));

            // 指定图表的配置项和数据

            var option = {

                // Make gradient line here
                visualMap: {
                    show: false,
                    type: 'continuous',
                    seriesIndex: 0,
                    color:['red','orange','yellow','lightskyblue']
                },

                title: {
                    left: 'center',
                    text: '每天的动态次数(页内滚动鼠标或拖动下方进度条，可缩放数据)',
                    subtext:'数据来源: www.jianshu.com'
                },
                tooltip: {
                    trigger: 'axis'
                },
                xAxis: {
                    data: ${days},
            name:'日期'
            },
            yAxis: {
                splitLine: {show: false},
                name:'动态次数'
            },
            grid: {
                bottom: '10%',
                        top: '12%'
            },
            series: {
                type: 'line',
                        showSymbol: false,
                        data: ${daysfreq}
            },
            dataZoom: [{
                type: 'slider',
                show:true,
                start: 0,
                end:100
            },
                {
                    type:'inside',
                    start: 0,
                    end:100
                }]
            };
            //*必须，绑定图表自适应功能
            window.onresize = function () {
                myChart2.resize();
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart2.setOption(option);

        </script>
    </div>

    <div class="section" id="all_hour">
        <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
        <div id="hourline_chart" style="width:100%;height:600px;"></div>
        <script type="text/javascript">
            // 基于准备好的dom，初始化echarts实例
            var myChart3 = echarts.init(document.getElementById('hourline_chart'));

            // 指定图表的配置项和数据

            var option = {

                // Make gradient line here
                visualMap: {
                    show: false,
                    type: 'continuous',
                    seriesIndex: 0,
                    smooth:true,
                    color:['red','orange','yellow','lightskyblue']
                },

                title: {
                    left: 'center',
                    text: '一天中各时间点的动态次数(几点最活跃？)',
                    subtext:'数据来源: www.jianshu.com'
                },
                tooltip: {
                    trigger: 'axis'
                },
                xAxis: {
                    data: ['00:00','01:00','02:00','03:00','04:00','05:00','06:00','07:00','08:00','09:00','10:00','11:00','12:00',
                        '13:00','14:00','15:00','16:00','17:00','18:00','19:00','20:00','21:00','22:00','23:00'],
            name:'时间（24小时制）'
            },
            yAxis: {
                splitLine: {show: false},
                name:'动态次数'
            },
            grid:{
                bottom: '6%',
                        top: '10%'
            },
            series: {
                type: 'line',
                        showSymbol: false,
                        data: ${hourfreq}
            }
            };
            //*必须，绑定图表自适应功能
            window.onresize = function () {
                myChart3.resize();
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart3.setOption(option);

        </script>
    </div>

    <div class="section" id="all_week">
        <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
        <div id="weekline_chart" style="width:100%;height:600px;"></div>
        <script type="text/javascript">
            // 基于准备好的dom，初始化echarts实例
            var myChart4 = echarts.init(document.getElementById('weekline_chart'));

            // 指定图表的配置项和数据

            var option = {
                        tooltip : {
                            trigger: 'axis',
                            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                            }
                        },
                        title: {
                            left: 'center',
                            text: '一周中的动态情况',
                            subtext:'数据来源: www.jianshu.com'
                        },
                        grid: {
                            left: '7%',
                            right: '8%',
                            bottom: '8%'

                        },
                        xAxis : [
                            {
                                type : 'category',
                                data :  ["\u5468\u4e00", "\u5468\u4e8c", "\u5468\u4e09", "\u5468\u56db", "\u5468\u4e94", "\u5468\u516d", "\u5468\u65e5"],
                    axisTick: {
                alignWithLabel: true
            }
            }
            ],
            yAxis : [
                {
                    type : 'value',
                    name:'动态次数'
                }
            ],
                    series : [
                {
                    name:'动态次数',
                    type:'bar',
                    barWidth: '50%',
                    data:${weekfreq},
            itemStyle: {
                normal: {
                    color: function(params) {
                        //首先定义一个数组
                        var colorList = [
                            '#C33531','#EFE42A','#64BD3D','#EE9201','#29AAE3',
                            '#B74AE5','#0AAF9F','#E89589'
                        ];
                        return colorList[params.dataIndex]
                    }
                }
            }
            }
            ]
            };

            //*必须，绑定图表自适应功能
            window.onresize = function () {
                myChart4.resize();
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart4.setOption(option);

        </script>
    </div>

    <div class="section" id="share_note_month">
        <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
        <div id="share_month_chart" style="width:100%;height:600px;"></div>
        <script type="text/javascript">
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('share_month_chart'));

            // 指定图表的配置项和数据

            var option = {

                // Make gradient line here
                visualMap: {
                    show: false,
                    type: 'continuous',
                    seriesIndex: 0,
                    color:['red','orange','yellow','lightskyblue']
                },

                title: {
                    left: 'center',
                    text: '各个月份的发表文章次数',
                    subtext:'数据来源: www.jianshu.com'
                },
                tooltip: {
                    trigger: 'axis'
                },
                xAxis: {
                    data: ['1月份','2月份','3月份','4月份','5月份','6月份','7月份','8月份','9月份','10月份','11月份','12月份'],
            name:'月份'
            },
            yAxis: {
                splitLine: {show: false},
                name:'动态次数'
            },
            grid: {
                bottom: '6%',
                        top: '10%'
            },
            series: {
                type: 'line',
                        showSymbol: false,
                        data: ${monthsharenote},
                markPoint : {
                    data : [
                        {type : 'max',
                            name: '最大值'
                        }
                    ]
                },
                markLine: {
                    data: [
                        {type: 'average', name: '平均值',
                            label: {
                                normal: {
                                    position: 'end',
                                    formatter: '月平均值:{c}'
                                }
                            }},
                        {type: 'max', name: '最大值',
                            label: {
                                normal: {
                                    position: 'end',
                                    formatter: '最大值'
                                }
                            }}
                    ]
                }
            }
            };
            //*必须，绑定图表自适应功能
            window.onresize = function () {
                myChart.resize();
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);

        </script>
    </div>

    <div class="section" id="share_note_week_hour">
        <div align="center">
            <h4>一周中发表文章时间频率分布</h4>
            <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
            <div id="share_note_week_hour_chart" style="width:100%;height:580px;"></div>
            <script type="text/javascript">
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('share_note_week_hour_chart'));

                // 指定图表的配置项和数据

                var hours = ['12am', '1am', '2am', '3am', '4am', '5am', '6am',
                    '7am', '8am', '9am','10am','11am',
                    '12pm', '1pm', '2pm', '3pm', '4pm', '5pm',
                    '6pm', '7pm', '8pm', '9pm', '10pm', '11pm'];
                var days = ['周一', '周二',
                    '周三', '周四', '周五', '周六','周日'];

                var data = ${weektimesharenotefreq};


                var option = {
                    tooltip: {
                        position: 'left'
                    },
                    title: [],
                    color:['#FF6666','#EFE42A','#64BD3D','#EE9201','#29AAE3',
                        '#B74AE5','#0AAF9F','#E89589'],
                    singleAxis: [],
                    series: []
                };

                echarts.util.each(days, function (day, idx) {
                    option.title.push({
                        textBaseline: 'middle',
                        top: (idx + 0.5) * 100 / 7 + '%',
                        text: day
                    });
                    option.singleAxis.push({
                        left: 120,
                        type: 'category',
                        boundaryGap: false,
                        data: hours,
                        top: (idx * 100 / 7 + 5) + '%',
                        height: (100 / 7 - 10) + '%',
                        axisLabel: {
                            interval: 2
                        }
                    });
                    option.series.push({
                        singleAxisIndex: idx,
                        coordinateSystem: 'singleAxis',
                        type: 'scatter',
                        data: [],
                        symbolSize: function (dataItem) {
                            return dataItem[1]/6 * 50 > 120 ? 120:dataItem[1]/6 * 50;
                        }
                    });
                });

                echarts.util.each(data, function (dataItem) {
                    option.series[dataItem[0]].data.push([dataItem[1], dataItem[2]]);
                });

                //*必须，绑定图表自适应功能
                window.onresize = function () {
                    myChart.resize();
                };

                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);

            </script>
        </div>
    </div>


    <div class="section" id="like_note_week_hour">
        <div align="center">
            <h4>一周中喜欢文章时间频率分布</h4>
            <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
            <div id="like_note_week_hour_chart" style="width:100%;height:580px;"></div>
            <script type="text/javascript">
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('like_note_week_hour_chart'));

                // 指定图表的配置项和数据

                var hours = ['12am', '1am', '2am', '3am', '4am', '5am', '6am',
                    '7am', '8am', '9am','10am','11am',
                    '12pm', '1pm', '2pm', '3pm', '4pm', '5pm',
                    '6pm', '7pm', '8pm', '9pm', '10pm', '11pm'];
                var days = ['周一', '周二',
                    '周三', '周四', '周五', '周六','周日'];

                var data = ${weektimelikenotefreq};

                var option = {
                    tooltip: {
                        position: 'left'
                    },
                    title: [],
                    color:['#FF6666','#EFE42A','#64BD3D','#EE9201','#29AAE3',
                        '#B74AE5','#0AAF9F','#E89589'],
                    singleAxis: [],
                    series: []
                };

                echarts.util.each(days, function (day, idx) {
                    option.title.push({
                        textBaseline: 'middle',
                        top: (idx + 0.5) * 100 / 7 + '%',
                        text: day
                    });
                    option.singleAxis.push({
                        left: 120,
                        type: 'category',
                        boundaryGap: false,
                        data: hours,
                        top: (idx * 100 / 7 + 5) + '%',
                        height: (100 / 7 - 10) + '%',
                        axisLabel: {
                            interval: 2
                        }
                    });
                    option.series.push({
                        singleAxisIndex: idx,
                        coordinateSystem: 'singleAxis',
                        type: 'scatter',
                        data: [],
                        symbolSize: function (dataItem) {
                            return dataItem[1]/6 * 50 > 120 ? 120:dataItem[1]/6 * 50;
                        }
                    });
                });

                echarts.util.each(data, function (dataItem) {
                    option.series[dataItem[0]].data.push([dataItem[1], dataItem[2]]);
                });

                //*必须，绑定图表自适应功能
                window.onresize = function () {
                    myChart.resize();
                };

                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);

            </script>
        </div>
    </div>
    <div class="section" id="like_user_week_hour">
        <div align="center">
            <h3>一周中关注用户时间频率分布</h3>
            <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
            <div id="like_user_week_hour_chart" style="width:100%;height:580px;"></div>
            <script type="text/javascript">
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('like_user_week_hour_chart'));

                // 指定图表的配置项和数据

                var hours = ['12am', '1am', '2am', '3am', '4am', '5am', '6am',
                    '7am', '8am', '9am','10am','11am',
                    '12pm', '1pm', '2pm', '3pm', '4pm', '5pm',
                    '6pm', '7pm', '8pm', '9pm', '10pm', '11pm'];
                var days = ['周一', '周二',
                    '周三', '周四', '周五', '周六','周日'];

                var data = ${weektimelikeuserfreq};

                var option = {
                    tooltip: {
                        position: 'left'
                    },
                    title: [],
                    color:['#FF6666','#EFE42A','#64BD3D','#EE9201','#29AAE3',
                        '#B74AE5','#0AAF9F','#E89589'],
                    singleAxis: [],
                    series: []
                };

                echarts.util.each(days, function (day, idx) {
                    option.title.push({
                        textBaseline: 'middle',
                        top: (idx + 0.5) * 100 / 7 + '%',
                        text: day
                    });
                    option.singleAxis.push({
                        left: 120,
                        type: 'category',
                        boundaryGap: false,
                        data: hours,
                        top: (idx * 100 / 7 + 5) + '%',
                        height: (100 / 7 - 10) + '%',
                        axisLabel: {
                            interval: 2
                        }
                    });
                    option.series.push({
                        singleAxisIndex: idx,
                        coordinateSystem: 'singleAxis',
                        type: 'scatter',
                        data: [],
                        symbolSize: function (dataItem) {
                            return dataItem[1]/6 * 50 > 120 ? 120:dataItem[1]/6 * 50;
                        }
                    });
                });

                echarts.util.each(data, function (dataItem) {
                    option.series[dataItem[0]].data.push([dataItem[1], dataItem[2]]);
                });

                //*必须，绑定图表自适应功能
                window.onresize = function () {
                    myChart.resize();
                };

                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);

            </script>
        </div>
    </div>

    <div class="section" id="comment_note_week_hour">
        <div align="center">
            <h4>一周中发表评论时间频率分布</h4>
            <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
            <div id="comment_note_week_hour_chart" style="width:100%;height:580px;"></div>
            <script type="text/javascript">
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('comment_note_week_hour_chart'));

                // 指定图表的配置项和数据

                var hours = ['12am', '1am', '2am', '3am', '4am', '5am', '6am',
                    '7am', '8am', '9am','10am','11am',
                    '12pm', '1pm', '2pm', '3pm', '4pm', '5pm',
                    '6pm', '7pm', '8pm', '9pm', '10pm', '11pm'];
                var days = ['周一', '周二',
                    '周三', '周四', '周五', '周六','周日'];

                var data = ${weektimecommentnotefreq};

                var option = {
                    tooltip: {
                        position: 'left'
                    },
                    title: [],
                    color:['#FF6666','#EFE42A','#64BD3D','#EE9201','#29AAE3',
                        '#B74AE5','#0AAF9F','#E89589'],
                    singleAxis: [],
                    series: []
                };

                echarts.util.each(days, function (day, idx) {
                    option.title.push({
                        textBaseline: 'middle',
                        top: (idx + 0.5) * 100 / 7 + '%',
                        text: day
                    });
                    option.singleAxis.push({
                        left: 120,
                        type: 'category',
                        boundaryGap: false,
                        data: hours,
                        top: (idx * 100 / 7 + 5) + '%',
                        height: (100 / 7 - 10) + '%',
                        axisLabel: {
                            interval: 2
                        }
                    });
                    option.series.push({
                        singleAxisIndex: idx,
                        coordinateSystem: 'singleAxis',
                        type: 'scatter',
                        data: [],
                        symbolSize: function (dataItem) {
                            return dataItem[1]/6 * 50 > 120 ? 120:dataItem[1]/6 * 50;
                        }
                    });
                });

                echarts.util.each(data, function (dataItem) {
                    option.series[dataItem[0]].data.push([dataItem[1], dataItem[2]]);
                });

                //*必须，绑定图表自适应功能
                window.onresize = function () {
                    myChart.resize();
                };

                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);

            </script>
        </div>
    </div>

    <div class="section" id="reward_note_week_hour">
        <div align="center">
            <h4>一周中打赏时间频率分布</h4>
            <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
            <div id="reward_note_week_hour_chart" style="width:100%;height:580px;"></div>
            <script type="text/javascript">
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('reward_note_week_hour_chart'));

                // 指定图表的配置项和数据

                var hours = ['12am', '1am', '2am', '3am', '4am', '5am', '6am',
                    '7am', '8am', '9am','10am','11am',
                    '12pm', '1pm', '2pm', '3pm', '4pm', '5pm',
                    '6pm', '7pm', '8pm', '9pm', '10pm', '11pm'];
                var days = ['周一', '周二',
                    '周三', '周四', '周五', '周六','周日'];

                var data = ${weektimerewardnotesfreq};

                var option = {
                    tooltip: {
                        position: 'left'
                    },
                    title: [],
                    color:['#FF6666','#EFE42A','#64BD3D','#EE9201','#29AAE3',
                        '#B74AE5','#0AAF9F','#E89589'],
                    singleAxis: [],
                    series: []
                };

                echarts.util.each(days, function (day, idx) {
                    option.title.push({
                        textBaseline: 'middle',
                        top: (idx + 0.5) * 100 / 7 + '%',
                        text: day
                    });
                    option.singleAxis.push({
                        left: 120,
                        type: 'category',
                        boundaryGap: false,
                        data: hours,
                        top: (idx * 100 / 7 + 5) + '%',
                        height: (100 / 7 - 10) + '%',
                        axisLabel: {
                            interval: 2
                        }
                    });
                    option.series.push({
                        singleAxisIndex: idx,
                        coordinateSystem: 'singleAxis',
                        type: 'scatter',
                        data: [],
                        symbolSize: function (dataItem) {
                            return dataItem[1]/6 * 50 > 120 ? 120:dataItem[1]/6 * 50;
                        }
                    });
                });

                echarts.util.each(data, function (dataItem) {
                    option.series[dataItem[0]].data.push([dataItem[1], dataItem[2]]);
                });

                //*必须，绑定图表自适应功能
                window.onresize = function () {
                    myChart.resize();
                };

                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);

            </script>
        </div>
    </div>


    <div class="section" id="comment_cloud">
        <div align="center">
            <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
            <h3>共写下评论 ${comment_num} 条，以下词语出现频率较高</h3>
            <div id="word_freq" style="width:100%;height:580px;"></div>
            <script>
                // 基于准备好的dom，初始化echarts图表
                var myChart = echarts.init(document.getElementById('word_freq'));

                function createRandomItemStyle() {
                    return {
                        normal: {
                            color: 'rgb(' + [
                                Math.round(Math.random() * 255),
                                Math.round(Math.random() * 255),
                                Math.round(Math.random() * 255)
                            ].join(',') + ')'
                        }
                    };
                }

                option = {
                    title: {
                        text: '',
                        link: 'http://www.google.com/trends/hottrends'
                    },
                    tooltip: {
                        show: true
                    },
                    series: [{
                        name: 'Google Trends',
                        type: 'wordCloud',
                        size: ['80%', '80%'],
                        textRotation : [0, 45, 90, -45],
                        textPadding: 0,
                        shape:'smooth',
                        autoSize: {
                            enable: true,
                            minSize: 14
                        },
                        textStyle: {
                            normal: {
                                color: function () {
                                    return 'rgb(' + [
                                        Math.round(Math.random() * 255),
                                        Math.round(Math.random() * 255),
                                        Math.round(Math.random() * 255)
                                    ].join(',') + ')';
                                }
                            }},
                        data: ${wordfreq}
                    }]
                };

                // 为echarts对象加载数据
                myChart.setOption(option,{notMerge:true,lazyUpdate:true});


            </script>
        </div>
    </div>

    <div class="section" id="end">
        <div align="center">
            <p style="font-size:72px;color:#FA8072;">谢谢观赏</p>
            <p style="font-size:32px;color:#FA8072">----------end----------</p>
        </div>
    </div>

</div>
</body>
</html>

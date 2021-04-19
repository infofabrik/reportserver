/**
 * Created by bugg on 17/06/14.
 */
var SplashScreen = Backbone.View.extend({
    events: {
        'click .run_query': 'run_query',
        'click .run_dashboards': 'run_dashboard',
        'click .head' : 'click_head',
        'click .beg_tut': 'run_tour'
    },
    click_head: function(event){
        event.preventDefault();
        var target = event.target;
        var a = $(target).attr('class').split(' ');
        $('nav li').removeClass('active');
        $(target).parent().addClass('active');
        $('.stabs section').hide();

        var active = "";
        if(a.indexOf("welcome") > -1){
            active = "welcome";
        }
        else if(a.indexOf("features") > -1){
            active = "features";
        }
        else if(a.indexOf("help") > -1){
            active = "help";
        }
        else if(a.indexOf("enterprise") > -1){
            active = "enterprise";
        }

        $('#'+active).fadeIn();

    },
    run_tour: function(){

        this.toolbar = Saiku.toolbar;

        var tour = new Tour({toolbar: this.toolbar});

        tour.run_tour();
    },
    initialize: function(args) {
        _.bindAll(this, "caption");
        _.extend(this, Backbone.Events);

    },
    run_query : function(){
        Saiku.tabs.add(new Workspace());
        return false;
    },
    run_dashboard : function(){
       
        return false;
    },
    template: function() {
        var template = $("<div></div>").html() || "";
        return _.template(template)({
            //    cube_navigation: Saiku.session.sessionworkspace.cube_navigation
        });

    },
    setupPage: function(obj){
        var height = $(window).height();
        $('body').height(height);
        $('.stabs section').each(function(){
            var vH = $(this).height();
            var dif = ((height - vH)/2)-50;
            if(dif<0){
                dif = 20;
            }
            //$(this).css('margin-top',dif+'px').hide();
        });
        var active = $('nav li.active a').attr('class');
        $('#'+active).fadeIn();
    },
    render: function(){
        var self = this;

        var license = new License();
		if(Settings.BIPLUGIN5){
                $(self.el).html(self.template());

//                if (Settings.LICENSE.licenseType != undefined &&
//                    Settings.LICENSE.licenseType != "trial" && Settings.LICENSE.licenseType != "Open Source License") {

                    $(self.el).find(".enterprisetoggle").css("visibility", "hidden");

//				}
                self.getContent();

                self.getNews();

                self.setupPage(self);
                $('#splash').find('> nav > ul > li.active > a').click(function(){
                    var active = $(this).attr('class');
                    $('nav li').removeClass('active');
                    $(this).parent().addClass('active');
                    $('.stabs section').hide();
                    $('#'+active).fadeIn();
                });
		}
		else {
                //$(self.el).html(self.template()).appendTo($('body'));
                $(self.el).html(self.template());

//                if (Settings.LICENSE.licenseType != undefined &&
//                    Settings.LICENSE.licenseType != "trial" && Settings.LICENSE.licenseType != "Open" +
//                    " Source License") {

                    $(self.el).find(".enterprisetoggle").css("visibility", "hidden");
//
//				}
                self.getContent();

                self.getNews();

                self.setupPage(self);
            $('#splash > nav > ul > li.active > a').click(function(){
                var active = $(this).attr('class');
                $('nav li').removeClass('active');
                $(this).parent().addClass('active');
                $('.stabs section').hide();
                $('#'+active).fadeIn();
            });

        }

      return this;
  },
    remove:function(){
        $(this.el).remove();
    },
    caption: function(increment) {
        return '<span class="i18n">Home</span>';
    },
	getNews: function(){
		
	},
    getContent: function(){

    }

});

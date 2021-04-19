var Tour = Backbone.View.extend({
    initialize: function(args) {
        _.bindAll(this, "run_tour");
        _.extend(this, Backbone.Events);


        this.toolbar = args.toolbar;


    },
    run_tour: function(){
    	
    }
});


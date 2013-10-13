var Race = Backbone.Model.extend({
  urlRoot:"http://hackru.alexvallorosi.com/races",
  defaults:{
    "id":null,
    "email":"",
    "vehicle":"",
    "map":"",
    "time":""
  }
});

var Races = Backbone.Collection.extend({
  model:Race,
  url:"http://hackru.alexvallorosi.com/races"
});

var RaceListView = Backbone.View.extend({

    template:_.template($('#tpl-race-list-item').html()),

    initialize:function () {
        console.log("RaceListView init");
        _.bindAll(this, "render");
        this.model.bind('change', this.render);
    },

    render:function (eventName) {
        var self = this;
        this.model.fetch().done(function() {
            console.log("RaceListView Render");
            console.warn(self.model);

            self.model.models.comparator = "time";
            self.model.models.sort();

            console.warn(self.model.models);

            _.each(self.model.models, function (model) {
                this.$el.append(this.template(model));
            }, self);
        });

        return this;
    }

});

var RaceView = Backbone.View.extend({

  events: {
    'click .save':'saveRace'
  },

  initialize: function () {
      this.model.bind('change', function(model){
            var confirmation = model.changedAttributes();
            for (var att in confirmation) {
            	console.log("KEY == "+att);
            	if (att == 'results'){
            		console.log(att);
            		var results = confirmation[att];
            		alert(results["message"]);
            	};
            }
      });
  },

  render: function (eventName) {
      console.log("RaceView Render");
      console.warn(this.model.toJSON());
      this.$el.html(this.template(this.model.toJSON()));
      return this;
  },

  saveRace: function () {
		console.log("save()");
    this.model.set({
        email: $('#email').val(),
        vehicle: $('#vehicle').val(),
        map: $('#map').val(),
        time: $('#time').val()
    });

    //this is stupid
    if (this.model.isNew()) {
      this.model.save();
    } else {
      this.model.save();
    }
    console.warn(this.model);
    return false;
  }
});

var AppRouter = Backbone.Router.extend({
    routes:{
        "":"list",
        "races/:id":"raceDetails"
    },

    list:function () {
		console.log("list()");
        // this.raceList = new Races();
        // this.raceList.fetch();

        var raceList = new Races();
        this.raceListView = new RaceListView({model:raceList, el:'#leaderboard'});

        this.raceListView.render();
        // $('#leaderboard').html(this.raceListView.render().el);
    },

    raceDetails:function (id) {
		console.log("raceDetails()");
        this.race = this.raceList.get(id);
        if (app.raceView) app.raceView.close();
        this.raceView = new RaceView({model:this.race, el:'.test'});
        $('#leaderboard').html(this.raceView.render().el);
    }
});

app = new AppRouter();
this.raceView = new RaceView({model:new Race, el:'.test'});
Backbone.history.start();
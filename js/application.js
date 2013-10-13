window.Race = Backbone.Model.extend({
  urlRoot:"http://hackru.alexvallorosi.com/races",
  defaults:{
    "id":null,
    "email":"",
    "vehicle":"",
    "map":"",
    "time":""
  }
});

window.Races = Backbone.Collection.extend({
  model:Race,
  url:"http://hackru.alexvallorosi.com/races"
});

var RaceListView = Backbone.View.extend({

    tagName:'ul',

    initialize:function () {
        console.log("RaceListView init");
        this.model.bind("reset", this.render, this);
    },

    render:function (eventName) {
        console.log("RaceListView Render");
        _.each(this.model.models, function (model) {
            $('#leaderboard').append(new RaceListItemView({model:model}).render().el);
        }, this);
        return this;
    }

});

var RaceListItemView = Backbone.View.extend({

    tagName:'li',

    template:_.template($('#tpl-race-list-item').html()),

    render:function (eventName) {
        console.log("RaceListItemView Render");
        $(this.el).html(this.template(this.model.toJSON()));
        return this;
    }
});

var RaceView = Backbone.View.extend({

  template:_.template($('#tpl-race-details').html()),

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
      $(this.el).html(this.template(this.model.toJSON()));
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
        this.raceList = new Races();
        console.warn(this.raceList);
        this.raceListView = new RaceListView({model:this.raceList, el:'#leaderboard'});
        this.raceList.fetch();
        $('#sidebar').html(this.raceListView.render().el);
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
window.Race = Backbone.Model.extend({
  urlRoot:"http://do.alexvallorosi.com/races",
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
  url:"http://do.alexvallorosi.com/races"
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

  render: function () {
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
      app.raceList.create(this.model);
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
        "race/:id":"raceDetails"
    },

    list:function () {
		console.log("list()");
        this.raceList = new Races();
        this.raceList.fetch();
    },

    raceDetails:function (id) {
		console.log("raceDetails()");

        this.race = this.raceList.get(id);
        if (app.raceView) app.raceView.close();
        this.raceView = new RaceView({model:this.race, el:'.test'});
    }
});

app = new AppRouter();
this.raceView = new RaceView({model:new Race, el:'.test'});
Backbone.history.start();
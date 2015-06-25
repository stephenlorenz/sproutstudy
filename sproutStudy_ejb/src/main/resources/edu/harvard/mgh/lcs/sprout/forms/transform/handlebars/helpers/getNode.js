Handlebars.registerHelper('getNode', function (sourceNode, queryKey, options) {

    var result;

    if (arguments.length < 2) {
        throw new Error("Handlerbars Helper 'getNode' needs 2 parameters");
    }

    if (sourceNode !== undefined && Array.isArray(sourceNode)) {
        for (var i = 0; i < sourceNode.length; i++) {
            var key = Object.keys(sourceNode[i]);
            if (key == queryKey) {
                result = sourceNode[i][key];
            }
        }
    }

    return options.fn(result);

});
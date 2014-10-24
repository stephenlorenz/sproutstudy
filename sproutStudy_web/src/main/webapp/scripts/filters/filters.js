sproutStudyApp.filter('filterUnique', function () {
    return function (items, inbox) {
        var filtered = [];

        if (Array.isArray(items) && Array.isArray(inbox)) {
            for (var i=0;i<items.length;i++) {
                var addInd = true;
                var item = items[i];
                if (item.unique) {
                    for (var i2=0;i2<inbox.length;i2++) {
                        var form = inbox[i2];
                        if (form.publicationKey == item.publicationKey) addInd = false;
                    }
                }
                if (addInd) filtered.push(item);
            }
        }
        return filtered;
    };
});

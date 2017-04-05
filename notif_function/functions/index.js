var functions = require('firebase-functions');
const admin = require('firebase-admin');
admin.initializeApp(functions.config().firebase);

exports.commentNotify = functions.database.ref('/post-comments/{postId}/{commentId}')
    .onWrite(event => {
        const postId = event.params.postId;
        const comment = event.data.val();
        console.log("User : " + comment.uid + ", commented on post : " + event.params.postId);
        admin.database().ref('/posts/' + postId + '/uid').once('value', function(snapshot){
            if(comment.upvoteCount > 0)
                return;
            if(comment.uid == snapshot.val())
                return;
            admin.database().ref('/users/' + comment.uid + '/full_name').once('value', function(name){
            admin.database().ref('/users/' + snapshot.val() + '/instanceId').once('value', function(snapshot){
                if(snapshot != null) {
                    const payload = {
                        data: {
                            postKey: postId,
                            body: name.val() + " commented on your post",
                            title: "New comment"
                        }
                    };
                    admin.messaging().sendToDevice(snapshot.val(), payload)
                        .then(function(response){
                            console.log("Message sent!!");
                        });
                } else {
                    console.log("No Instance ID");
                }
                // to get who posted the comment '/post-comments/{postId}/{commentId}/uid'
            });
        });});
    });

exports.upvotePostNotify = functions.database.ref('/posts/{postId}/stars/{userId}').onWrite(event => {
    console.log("User : " + event.params.userId + ", upvoted post : " + event.params.postId);
    var postId = event.params.postId;
    var user_who_upvoted = event.params.userId;
    admin.database().ref('/posts/' + postId + '/uid').once('value', function(snapshot){
        console.log("Send notif to user " + snapshot.val());
        if(user_who_upvoted == snapshot.val())
            return;
        admin.database().ref('/users/' + user_who_upvoted + '/full_name').once('value', function(name){
        admin.database().ref('/users/' + snapshot.val() + '/instanceId').once('value', function(snapshot){
            if(snapshot != null) {
                const payload = {
                    data: {
                        postKey: postId,
                        body: name.val() + " upvoted your post",
                        title: "Post upvote"
                    }
                };
                admin.messaging().sendToDevice(snapshot.val(), payload)
                    .then(function(response){
                        console.log("Message sent!!");
                    });
            } else {
                console.log("No Instance ID");
            }
            // to get who posted the comment '/post-comments/{postId}/{commentId}/uid'
        });
    });});
})

exports.upvoteComment = functions.database.ref('/post-comments/{postId}/{commentId}/upvoteusers/{userId}').onWrite(event => {
    console.log("User : " + event.params.userId + ", upvoted post : " + event.params.postId);
    var postId = event.params.postId;
    var user_who_upvoted = event.params.userId;
    admin.database().ref('/post-comments/' + postId + '/' + event.params.commentId + '/uid').once('value', function(snapshot){
        console.log("Send notif to user " + snapshot.val());
        if(user_who_upvoted == snapshot.val())
            return;
        admin.database().ref('/users/' + user_who_upvoted + '/full_name').once('value', function(name){
        admin.database().ref('/users/' + snapshot.val() + '/instanceId').once('value', function(snapshot){
            if(snapshot != null) {
                const payload = {
                    data: {
                        postKey: postId,
                        body: name.val() + " upvoted your comment",
                        title: "Comment upvote"
                    }
                };
                admin.messaging().sendToDevice(snapshot.val(), payload)
                    .then(function(response){
                        console.log("Message sent!!");
                    });
            } else {
                console.log("No Instance ID");
            }
            // to get who posted the comment '/post-comments/{postId}/{commentId}/uid'
        });
    });});
})
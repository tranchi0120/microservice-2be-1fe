$(document).ready(function () {
    let selectedCommentId = null;
    let getIdMode = false;

    function fetchComment() {
        $.ajax({
            url: "/api/comments", type: "GET", dataType: "json", success: function (response) {
                let commentListHtml = "";
                $.each(response, function (index, comment) {
                    commentListHtml += "<tr>";
                    commentListHtml += "<td>" + (index + 1) + "</td>";
                    commentListHtml += "<td>" + comment.content + "</td>";
                    commentListHtml += "<td>" + comment.post.title + "</td>";
                    commentListHtml += "<td>" + comment.post.user.username + "</td>";
                    commentListHtml += "<td class='action'>" + "<a class='icons icons-edit' data-toggle='modal' " + "data-target='#exampleModalCenter' data-id='" + comment.id + "' href='#'>" + "<i class='ri-pencil-line'></i></a>" + "<a class='icons icons-delete' data-toggle='modal' " + "data-target='#exampleModalDelete' data-id='" + comment.id + "' href='#'>" + "<i class='ri-delete-bin-7-line'></i></a>" + "</td>";
                    commentListHtml += "</tr>";
                });
                $("#commentTable tbody").html(commentListHtml); // Hiển thị danh sách người dùng trong bảng
            }, error: function () {
                console.log("Error occurred while loading user list");
            }
        });
    }

    // lấy user để thêm vào post
    function fetchUsers() {
        $.ajax({
            type: "GET", url: "/api/users", success: function (data) {
                let selectUser = $("#selectUser");
                selectUser.empty();
                selectUser.append("<option value='' selected>Select User</option>");
                $.each(data, function (index, user) {
                    selectUser.append("<option value='" + user.id + "'>" + user.username + "</option>");
                });
            }, error: function (error) {
                console.log("Lỗi khi lấy danh sách người dùng: " + error.responseText);
            }
        });
    }


    function fetchPost() {
        $.ajax({
            type: "GET", url: "/api/posts", success: function (data) {
                let selectUser = $("#selectPost");
                selectUser.empty();
                selectUser.append("<option value='' selected>Select Post</option>");
                $.each(data, function (index, post) {
                    selectUser.append("<option value='" + post.id + "'>" + post.title + "</option>");
                });
            }, error: function (error) {
                console.log("Lỗi khi lấy danh sách người dùng: " + error.responseText);
            }
        });
    }

    fetchPost();
    fetchUsers();
    fetchComment();


    $(document).on("click", ".icons", function () {
        let commentId = $(this).data("id");
        selectedCommentId = commentId;

        if (commentId) {
            $.ajax({
                url: "/api/comments/" + commentId,
                type: "GET",
                dataType: "json",
                success: function (comment) {
                    getIdMode = true;
                    $("#selectPost, #selectUser").css("pointer-events", "none");
                    $("#selectPost, #selectUser").css("opacity", "0.5");
                    $("#inputContent").val(comment.content);
                    $("#selectPost").val(comment.post.id);
                    $("#selectUser").val(comment.user.id);
                }, error: function () {
                    console.log("Error occurred while loading user details");
                }
            });
        }
    });

    $("#btnDelete").on("click", function () {
        if (selectedCommentId) {
            $.ajax({
                type: "DELETE", url: "/api/comments/delete/" + selectedCommentId,
                success: function () {
                    $("#exampleModalDelete").modal("hide");
                    fetchComment();
                    handleModalClose();
                }, error: function (error) {
                    alert("delete fail");
                    console.error("Error deleting post");
                }
            });
        }
    });


    $("#btnSave").on("click", function () {
        let content = $("#inputContent").val();
        let postId = $("#selectPost").val();
        let userId = $("#selectUser").val();

        if (content.trim() === '' || postId.trim() === '' || userId === '') {
            alert("Please fill in all input.");
            return;
        }

        if (userId === '' || postId === '') {
            alert("Please select a user.");
            return;
        }
        userId = parseInt(userId);
        postId = parseInt(postId);

        // Tạo đối tượng JSON để gửi lên server
        let commentData = {
            content, post: {id: postId}, user: {id: userId}
        };


        if (selectedCommentId) {
            $.ajax({
                type: "PUT",
                contentType: "application/json",
                url: "/api/comments/update/" + selectedCommentId,
                data: JSON.stringify(commentData),
                dataType: 'json',
                success: function () {
                    $("#exampleModalCenter").modal("hide");
                    fetchComment();
                },
                error: function (error) {
                    console.error("Error updating user");
                }
            });

        } else {
            $.ajax({
                type: "POST",
                url: "/api/comments/addComment",
                contentType: "application/json",
                data: JSON.stringify(commentData),
                success: function () {
                    $("#exampleModalCenter").modal("hide");
                    fetchComment();
                },
                error: function (error) {
                    console.log("Lỗi khi thêm bài viết");
                }
            });
        }
    });

    // khi đóng modal lại thì reset data
    function handleModalClose() {
        fetchComment();
        selectedCommentId = null;
        if (getIdMode) {
            $("#selectPost, #selectUser").css("pointer-events", "auto");
            $("#selectPost, #selectUser").css("opacity", "1");
        }
        $("#inputContent").val("");
        $("#selectPost").val("");
        $("#selectUser").val("");

        getIdMode = false; // Reset isEditMode
    }

    $("#exampleModalCenter").on("hidden.bs.modal", handleModalClose);
    $("#exampleModalDelete").on("hidden.bs.modal", handleModalClose);
});

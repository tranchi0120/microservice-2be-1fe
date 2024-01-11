$(document).ready(function () {
    let selectedPostId = null;
    let getIdMode = false;

    function fetchPosts() {
        $.ajax({
            url: "/api/posts", type: "GET", dataType: "json", success: function (response) {
                let userListHtml = "";
                $.each(response, function (index, post) {
                    userListHtml += "<tr>";
                    userListHtml += "<td>" + (index + 1) + "</td>";
                    userListHtml += "<td>" + post.title + "</td>";
                    userListHtml += "<td>" + post.content + "</td>";
                    userListHtml += "<td>" + post.user.username + "</td>";
                    userListHtml += "<td class='action'>" +
                        "<a class='icons icons-edit' data-toggle='modal' " +
                        "data-target='#exampleModalCenter' data-id='" + post.id + "' href='#'>" +
                        "<i class='ri-pencil-line'></i></a>" +
                        "<a class='icons icons-delete' data-toggle='modal' " +
                        "data-target='#exampleModalDelete' data-id='" + post.id +
                        "' href='#'>" + "<i class='ri-delete-bin-7-line'></i></a>" + "</td>";
                    userListHtml += "</tr>";
                });
                $("#userTable tbody").html(userListHtml);
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

    fetchPosts();
    fetchUsers();


    $(document).on("click", ".icons", function () {
        let postId = $(this).data("id");
        selectedPostId = postId;

        if (postId) {
            $.ajax({
                url: "/api/posts/" + postId,
                type: "GET",
                dataType: "json",
                success: function (post) {
                    getIdMode = true;
                    $("#selectUser").css("pointer-events", "none");
                    $("#selectUser").css("opacity", "0.5");
                    $("#inputTitle").val(post.title);
                    $("#inputContent").val(post.content);
                    $("#selectUser").val(post.user.id);
                }, error: function () {
                    console.log("Error occurred while loading user details");
                }
            });
        }
    });

    $("#btnDelete").on("click", function () {

        if (selectedPostId) {
            $.ajax({
                type: "DELETE", url: "/api/posts/delete/" + selectedPostId, success: function () {
                    $("#exampleModalDelete").modal("hide");
                    fetchPosts();
                    handleModalClose();
                }, error: function (error) {
                    alert("delete fail");
                    console.error("Error deleting post");
                }
            });
        }
    });


    $("#btnSave").on("click", function () {
        let title = $("#inputTitle").val();
        let content = $("#inputContent").val();
        let userId = $("#selectUser").val();

        if (title.trim() === '' || content.trim() === '' || userId === '') {
            alert("Please fill in all input.");
            return;
        }

        if (userId === '') {
            alert("Please select a user.");
            return;
        }
        userId = parseInt(userId);

        // Tạo đối tượng JSON để gửi lên server
        let postData = {
            title, content, user: {id: userId}
        };


        if (selectedPostId) {
            $.ajax({
                type: "PUT",
                contentType: "application/json",
                url: "/api/posts/update/" + selectedPostId,
                data: JSON.stringify(postData),
                dataType: 'json',
                success: function (data) {
                    console.log("post updated successfully:", data);
                    $("#exampleModalCenter").modal("hide");
                    fetchPosts();
                },
                error: function (error) {
                    console.error("Error updating user");
                }
            });

        } else {
            $.ajax({
                type: "POST",
                url: "/api/posts/addPost",
                contentType: "application/json",
                data: JSON.stringify(postData),
                success: function () {
                    $("#exampleModalCenter").modal("hide");
                    fetchPosts();
                },
                error: function (error) {
                    console.log("Lỗi khi thêm bài viết");
                }
            });
        }
    });

    // khi đóng modal lại thì reset data
    function handleModalClose() {
        // Reset dữ liệu và các giá trị
        $("#inputTitle").val("");
        $("#inputContent").val("");
        $("#selectUser").val("");
        $("#selectUser").css("pointer-events", "auto");
        $("#selectUser").css("opacity", "1");

        selectedPostId = null;
        getIdMode = false; // Reset các biến
    }

    $("#exampleModalCenter").on("hidden.bs.modal", handleModalClose);
    $("#exampleModalDelete").on("hidden.bs.modal", handleModalClose);

});




$(document).ready(function () {
    // để ấy userId
    let selectedUserId = null;

    function loadUserList() {
        $.ajax({
            url: "/api/users",
            type: "GET",
            dataType: "json",
            success: function (response) {
                let userListHtml = "";
                $.each(response, function (index, user) {
                    userListHtml += "<tr>";
                    userListHtml += "<td>" + (index + 1) + "</td>";
                    userListHtml += "<td>" + user.username + "</td>";
                    userListHtml += "<td>" + user.email + "</td>";
                    userListHtml += "<td class='action'><a class='icons icons-edit' data-toggle='modal' " +
                        "data-target='#exampleModalCenter' " +
                        "data-id='" + user.id + "' href='#'><i class='ri-pencil-line'></i></a></td>";
                    userListHtml += "</tr>";
                });
                $("#userTable tbody").html(userListHtml); // Hiển thị danh sách người dùng trong bảng
            },
            error: function () {
                console.log("Error occurred while loading user list");
            }
        });
    }

    loadUserList();

    $(document).on("click", ".icons-edit", function () {
        let userId = $(this).data("id");
        selectedUserId = userId;

        if (userId) {
            // Gọi AJAX để lấy thông tin chi tiết của người dùng
            $.ajax({
                url: "/api/users/" + userId,
                type: "GET",
                dataType: "json",
                success: function (user) {
                    // Hiển thị thông tin chi tiết của người dùng lên form
                    $("#inputUserId").val(user.id);
                    $("#inputUsername").val(user.username);
                    $("#inputEmail").val(user.email);

                    // Hiển thị modal và là Update
                    $("#exampleModalCenter").modal("show");

                },
                error: function () {
                    console.log("Error occurred while loading user details");
                }
            });
        }
    });

    $("#btnSave").click(function () {
        let username = $("#inputUsername").val();
        let email = $("#inputEmail").val();

        // Tạo đối tượng JSON từ dữ liệu người dùng nhập
        let newUser = { username, email };

        if (selectedUserId) {
            // Nếu là Update, thì lấy id từ trường ẩn
            $.ajax({
                type: "PUT",
                contentType: "application/json",
                url: "/api/users/update/" + selectedUserId,
                data: JSON.stringify(newUser),
                dataType: 'json',
                success: function (data) {
                    console.log("User updated successfully:", data);
                    $("#exampleModalCenter").modal("hide");

                    loadUserList();
                },
                error: function (error) {
                    console.error("Error updating user");
                }
            });
        } else {
            // Gửi yêu cầu AJAX để thêm người dùng
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/api/users/addUser",
                data: JSON.stringify(newUser),
                dataType: 'json',
                success: function (data) {
                    console.log("User added successfully:", data);
                    $("#exampleModalCenter").modal("hide");
                    loadUserList();
                },
                error: function (error) {
                    console.error("Error adding user:", error.responseText);

                    $("#emailError").text("Error: Email already exists");
                }
            });
        }
    });

    $("#exampleModalCenter").on("hidden.bs.modal", function () {
        // Reset giá trị trên các trường input
        $("#inputUserId").val("");
        $("#inputUsername").val("");
        $("#inputEmail").val("");
        $("#emailError").text("");

    });
});

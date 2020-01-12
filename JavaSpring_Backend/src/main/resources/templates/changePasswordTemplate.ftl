<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>


    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>

    <!-- use the font -->
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            font-size: 48px;
        }
    </style>
</head>
<body style="margin: 0; padding: 0;">

    <table align="center" border="0" cellpadding="0" cellspacing="0" width="600" style="border-collapse: collapse;">
        <tr>
            <td align="center" bgcolor="#ff4d4d" style="padding: 40px 0 30px 0;">
                <h3>Message from Restaurant Service App</h3>
            </td>
        </tr>
        <tr>
            <td bgcolor="#eaeaea" style="padding: 40px 30px 40px 30px;">
            	<b>
                <p>Dear ${userName},</p>
                <p>You send a request to change the password for your account. To complete this option you need to <a href="http://localhost:4200/changePasswordAccount?emailAccount=${userEmail}" rel="link">click here !</a> </p>
                <br>
                <p><br><br><br>Best regards, Restaurant Service Team.</p>
            </td>
        </tr>
    </table>

</body>
</html>
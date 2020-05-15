let router = module.exports = require('express').Router();
const sqlite3 = require('sqlite3').verbose();

let db = new sqlite3.Database('./schema.sql', (err) => {
	if (err) {
	  return console.error(err.message);
	}
	console.log('Connected to the in-memory SQlite database.');
  });


router.get('/users', function (req, rsp) {
	req.db.all('select * from users', (err, users) => {
		if (users) rsp.status(200).json(users);
		else console.log("noo"); //rsp.status(404).json({ error: "User not found" });
	})
	
})

router.post('/users', function (req, rsp) {
	req.db.all('insert into users values ($user_name, $password, $score)',
	{
		$user_name: req.body.user_name,
		$password: req.body.password,
		$score: req.body.score
	}, function (err) {
		if (!err) {
		rsp.status(200).json("User added");
		}
		else rsp.status(404).json("Fail adding user")
	})
})

router.put('/:user_name', function (req, rsp) {
	req.db.run('update users set score=? where user_name=?', req.body.score, req.params.user_name, function (err) {
	if (!err) rsp.status(200).json("User updated")
		else crsp.status(400).json("Fail to update this user")
	})
})

router.delete('/:user_name', function(req, rsp) {
	req.db.run('DELETE FROM users WHERE user_name=?', req.params.user_name, function (err) {
		if (!err) rsp.status(200).json("User updated")
			else rsp.status(400).json("Fail to update this user")
		})
})

router.use(function (req, rsp) {
	rsp.status(404).json({ error: "No such route found" })
});
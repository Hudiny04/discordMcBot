const { SlashCommandBuilder } = require('@discordjs/builders');
const axios = require('axios');
require('dotenv').config();


module.exports = {
    data: new SlashCommandBuilder()
    .setName('giveallcookie')
    .setDescription('gives all players on server cookie'),
async execute(interaction) {
     axios.get(`${process.env.API}:${process.env.PORT}/give-all-cookie`
    ).then(function (response) {
        interaction.reply(response.data);
    }).catch(function (error) {
        console.log(error);
        interaction.reply("Something went wrong");
      })
    return
},
};
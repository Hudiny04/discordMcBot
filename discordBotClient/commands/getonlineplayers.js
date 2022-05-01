const { SlashCommandBuilder } = require('@discordjs/builders');
const axios = require('axios');
require('dotenv').config();


module.exports = {
    data: new SlashCommandBuilder()
    .setName('getonlineplayers')
    .setDescription('Gets all online players from the server'),
async execute(interaction) {
     axios.get(`${process.env.API}${process.env.PORT}/get-online-players`
    ).then(function (response) {
        interaction.reply(response.data);
    }).catch(function (error) {
        console.log(error);
        interaction.reply("Something went wrong");
      })
    return
},
};
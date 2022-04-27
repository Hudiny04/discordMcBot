const { SlashCommandBuilder } = require('@discordjs/builders');
const axios = require('axios');
require('dotenv').config();


module.exports = {
    data: new SlashCommandBuilder()
    .setName('getonlineplayers')
    .setDescription('Getting all online players from server'),
async execute(interaction) {
     axios.get(`${process.env.API}/get-online-players`
    ).then(function (response) {
        interaction.reply(response.data);
    }).catch(function (error) {
        console.log(error);
        interaction.reply("Something went wrong");
      })
    return
},
};
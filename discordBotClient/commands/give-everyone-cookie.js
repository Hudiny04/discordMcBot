const { SlashCommandBuilder } = require('@discordjs/builders');
const axios = require('axios');
require('dotenv').config();


module.exports = {
    data: new SlashCommandBuilder()
    .setName('give-everyone-cookie')
    .setDescription('gives all players on server cookie'),
async execute(interaction) {
     axios.get(`${process.env.API}:${process.env.PORT}/give-everyone-cookie`
    ).then(function (response) {
        interaction.reply(response.data);
    }).catch(function (error) {
        console.log(error);
        interaction.reply("Something went wrong");
      })
    return
},
};